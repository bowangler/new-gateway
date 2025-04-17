package cn.hots.gateway.protocol.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @description: 实现逻辑将请求返回存入mongodb
 * @author
 * @date 2025/4/17 16:54
 * @version 1.0
 */
public class LoggingHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        byte[] data = (byte[]) msg;
        System.out.println("Received: " + msg);
        ctx.fireChannelRead(msg);
    }
}