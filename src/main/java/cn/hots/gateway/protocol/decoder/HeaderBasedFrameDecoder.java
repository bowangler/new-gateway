package cn.hots.gateway.protocol.decoder;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author TIT
 * @version 1.0
 * @description: TODO
 * @date 2025/4/15 18:20
 */
@Slf4j
public class HeaderBasedFrameDecoder extends ByteToMessageDecoder {


    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        try {

            String header = in.readCharSequence(4, StandardCharsets.UTF_8).toString();
            int bodyLength = Integer.parseInt(header.trim());
            String body = in.readCharSequence(bodyLength, StandardCharsets.UTF_8).toString();
            String msg = header + body;
            out.add(msg);
        } catch (Exception e) {
//            log.error("Protocol parse failed", e);
//            ctx.close();
            // 记录错误日志（包含远程IP信息）
            String clientIp = ((InetSocketAddress)ctx.channel().remoteAddress())
                    .getAddress().getHostAddress();
            log.error("Protocol violation from {}: {}", clientIp, e.getMessage());

            // 发送错误响应并关闭连接
            ByteBuf errorResp = Unpooled.copiedBuffer(
                    "PROTOCOL_ERROR", StandardCharsets.UTF_8);
            ctx.writeAndFlush(errorResp)
                    .addListener(ChannelFutureListener.CLOSE);
        }
    }
}