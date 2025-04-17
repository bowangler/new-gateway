package cn.hots.gateway.server;

import cn.hots.gateway.config.ProtocolInitializer;
import cn.hots.gateway.config.ProtocolRegistry;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.ArrayList;
import java.util.List;

public class NettyServer {
    private EventLoopGroup bossGroup;
    private EventLoopGroup workerGroup;
    private ProtocolRegistry registry;
    private List<Integer> ports;

    public NettyServer(ProtocolRegistry registry, List<Integer> ports) {
        this.registry = registry;
        this.ports = ports;
    }

    public void start() throws Exception {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            // 根据端口号获取对应的Initializer
                            ProtocolInitializer initializer = registry.getInitializer(ch.localAddress().getPort());
                            if (initializer != null) {
                                initializer.initChannel(ch);
                            } else {
                                ch.close(); // 没有对应的Initializer则关闭连接
                            }
                        }
                    })
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            // 保存所有ChannelFuture以便后续等待关闭
            List<ChannelFuture> futures = new ArrayList<>();
            for (Integer port : ports) {
                ChannelFuture f = b.bind(port).sync();
                System.out.println("Server started on port " + port);
                futures.add(f);
            }

            // 等待所有服务器通道关闭
            for (ChannelFuture future : futures) {
                future.channel().closeFuture().sync();
            }
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
}
