package cn.hots.gateway.protocol.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;


// 解密处理器
public class DecryptHandler extends ChannelInboundHandlerAdapter {
    private final String key;

    public DecryptHandler(String key) {
        this.key = key;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
//        byte[] encrypted = (byte[]) msg;
//        byte[] decrypted = decrypt(encrypted, key); // 实现解密算法
        ctx.fireChannelRead(msg);
    }
    private byte[] decrypt(byte[] encrypted, String key) {
        return encrypted;
    }
}
