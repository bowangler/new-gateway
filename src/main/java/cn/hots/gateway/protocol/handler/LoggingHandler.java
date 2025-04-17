package cn.hots.gateway.protocol.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

// 日志处理器
public class LoggingHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        byte[] data = (byte[]) msg;
        System.out.println("Received: " + msg);
        ctx.fireChannelRead(msg);
    }
}