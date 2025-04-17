package cn.hots.gateway.config;

import io.netty.channel.Channel;

/**
 * @author TIT
 * @version 1.0
 * @description: TODO
 * @date 2025/4/17 10:45
 */
public interface ProtocolInitializer {
    int getPort();
    void initChannel(Channel channel);
}
