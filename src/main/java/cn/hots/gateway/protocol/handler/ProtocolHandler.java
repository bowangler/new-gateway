package cn.hots.gateway.protocol.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

// 协议业务处理器
public class ProtocolHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        byte[] request = (byte[]) msg;
//        byte[] response = processBusiness(request); // 实现业务逻辑
        String response = processBusiness((String) msg);
        ctx.writeAndFlush(response);
    }

    private String processBusiness(String request) {
        // 示例业务处理：原样返回
        return request;
    }
//    private byte[] processBusiness(byte[] request) {
        // 示例业务处理：原样返回
//        return request;
//    }
}
