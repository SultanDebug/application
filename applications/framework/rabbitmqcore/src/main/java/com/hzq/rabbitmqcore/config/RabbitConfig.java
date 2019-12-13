package com.hzq.rabbitmqcore.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Huangzq
 * @title: RabbitConfig
 * @projectName applications
 * @date 2019/12/13 15:11
 */
@Slf4j
//@Configuration
@Component
@ConfigurationProperties(prefix = "spring.rabbitmq")
@Data
public class RabbitConfig {
    private String host;

    private int port;

    private String username;

    private String password;
}
