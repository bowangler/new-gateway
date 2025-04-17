package cn.hots.gateway.config;

import cn.hots.gateway.protocol.decoder.HeaderBasedFrameDecoder;
import cn.hots.gateway.protocol.decoder.MessageEncoder;
import cn.hots.gateway.protocol.handler.MessageHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelPipeline;
import org.springframework.stereotype.Component;

/**
 * @author TIT
 * @version 1.0
 * @description: TODO
 * @date 2025/4/17 11:10
 */
@Component
public class Port52003Initializer implements ProtocolInitializer{

    @Override
    public int getPort() {
        return 52003;
    }

    @Override
    public void initChannel(Channel channel) {
        ChannelPipeline pipeline = channel.pipeline();
        pipeline.addLast(new HeaderBasedFrameDecoder());
        pipeline.addLast(new MessageEncoder());
        pipeline.addLast(new MessageHandler());
    }
}
