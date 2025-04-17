package cn.hots.gateway.protocol.handler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

/**
 * @description: 实现逻辑将
 * @author
 * @date 2025/4/17 16:54
 * @version 1.0
 */
public class MongoLoggingHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof ByteBuf) {
            ByteBuf buf = (ByteBuf) msg;
            String receivedData = buf.toString(CharsetUtil.UTF_8);
            buf.retain(); // 保留引用以防止自动释放
            saveToMongoDB(receivedData, "READ");
        }
        ctx.fireChannelRead(msg); // 继续传递消息到下一个处理器
    }

    //write需要另外继承ChannelOutboundHandlerAdapter
//    @Override
//    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
//        if (msg instanceof ByteBuf) {
//            ByteBuf buf = (ByteBuf) msg;
//            String sendData = buf.toString(CharsetUtil.UTF_8);
//            buf.retain(); // 保留引用以防止自动释放
//            saveToMongoDB(sendData, "WRITE");
//        }
//        ctx.write(msg, promise); // 继续写消息到下一个处理器
//    }

    private void saveToMongoDB(String data, String eventType) {
        // 保存数据到MongoDB数据库中
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
//        mongoClient.close(); // 关闭MongoDB客户端连接
//        super.channelInactive(ctx);
    }
}