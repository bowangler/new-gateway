package cn.hots.gateway.protocol.cebb;

import cn.hots.gateway.config.ProtocolInitializer;
import cn.hots.gateway.protocol.decoder.MessageEncoder;
import cn.hots.gateway.protocol.handler.MessageHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Component;

/**
 * @author TIT
 * @version 1.0
 * @description: TODO
 * @date 2025/4/17 11:10
 */
@Component
public class CebbInitializer implements ProtocolInitializer {

    @Override
    public int getPort() {
        return 52015;
    }

    @Override
    public void initChannel(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new CebbFrameDecoder());
        pipeline.addLast(new LoggingHandler(LogLevel.INFO));
        pipeline.addLast(new MessageEncoder());
        pipeline.addLast(new MessageHandler());
    }
}
