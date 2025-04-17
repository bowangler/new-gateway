package cn.hots.gateway.protocol.handler;

import cn.hots.gateway.protocol.decoder.MessageEncryptor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;

// 加密处理器
public class EncryptHandler extends ChannelOutboundHandlerAdapter {
    private MessageEncryptor encryptor;
    public EncryptHandler(MessageEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) {
//        byte[] response = (byte[]) msg;
//        byte[] encrypted = encrypt(response, key); // 实现加密算法
        ctx.write(msg, promise);
    }

    private byte[] encrypt(byte[] msg, String key) {return msg;}
}