package cn.hots.gateway.server;

import cn.hots.gateway.config.ProtocolRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author TIT
 * @version 1.0
 * @description: TODO
 * @date 2025/4/17 10:49
 */
@Component
public class NettyServerStarter implements ApplicationRunner {
    @Autowired
    private ProtocolRegistry registry;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("NettyServerStarter start");
        // 从registry获取所有已注册的端口
        List<Integer> ports = registry.getAllPorts();
        new NettyServer(registry, ports).start();
    }
}
