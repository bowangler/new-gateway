package cn.hots.gateway.protocol.decoder;

import io.netty.buffer.ByteBuf;

/**
 * @author TIT
 * @version 1.0
 * @description: TODO
 * @date 2025/4/17 10:40
 */
public interface ProtocolParser {
    Object parse(ByteBuf in) ;
    int headerLength();
}
