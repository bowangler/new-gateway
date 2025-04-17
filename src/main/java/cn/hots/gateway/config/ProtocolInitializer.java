package cn.hots.gateway.config;

import io.netty.channel.Channel;

/**
 * @author TIT
 * @version 1.0
 * @description: 协议初始化器接口,新增的渠道配置需要实现此接口
 * @date 2025/4/17 10:45
 */
public interface ProtocolInitializer {
    int getPort();
    void initChannel(Channel channel);
}
