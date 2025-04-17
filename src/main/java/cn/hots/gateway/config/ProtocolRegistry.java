package cn.hots.gateway.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author TIT
 * @version 1.0
 * @description: TODO
 * @date 2025/4/17 10:39
 */
@Component
public class ProtocolRegistry {
    private final Map<Integer, ProtocolInitializer> initializers = new ConcurrentHashMap<>();

    @Autowired
    public ProtocolRegistry(List<ProtocolInitializer> initializerList) {
        for (ProtocolInitializer initializer : initializerList) {
            initializers.put(initializer.getPort(), initializer);
        }
    }

    public ProtocolInitializer getInitializer(int port) {
        return initializers.get(port);
    }

    public List<Integer> getAllPorts() {
        return initializers.keySet().stream()
                .sorted()
                .collect(Collectors.toList());
    }
}