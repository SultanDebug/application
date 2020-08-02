package com.hzq.demoservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis配置
 * @author Huangzq
 * @date 2019-04-12
 */
@Component
@ConfigurationProperties(prefix = "spring.redis")
@Data
public class RedisCon {

    private String password;

    private String host;
    //prefix+参数名  对应于配置文件config.properties中的spring.redis.*信息
    private int port;

    private int timeout;

    private JedisPoolConfig pool;
}
