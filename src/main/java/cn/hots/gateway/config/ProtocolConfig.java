package cn.hots.gateway.config;

import cn.hots.gateway.protocol.decoder.MessageEncryptor;
import cn.hots.gateway.protocol.decoder.MessageValidator;
import cn.hots.gateway.protocol.decoder.ProtocolParser;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.Getter;
import lombok.Setter;

/**
 * @description: TODO 
 * @author TIT
 * @date 2025/4/17 10:39
 * @version 1.0
 */
@Getter
@Setter
public class ProtocolConfig {
    private int port;
    private ByteToMessageDecoder headDecoder;
    private MessageToByteEncoder responseEncoder;
    private SimpleChannelInboundHandler handler;
    private ProtocolParser parser;
    private MessageValidator validator;
    private MessageEncryptor encryptor;
    private int maxFrameLength = 1024;
    private long timeout = 3000;
}
