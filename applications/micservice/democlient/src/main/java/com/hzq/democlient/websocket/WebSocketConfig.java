package com.hzq.democlient.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * ws注册
 * @author Huangzq
 * @title: WebSocketConfig
 * @projectName qs-saas
 * @date 2019/12/31 14:28
 */
@Configuration
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
