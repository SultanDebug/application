package com.hzq.zkcore.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * zk参数获取
 * @author Huangzq
 * @title: ZkConfig
 * @projectName applications
 * @date 2019/7/4 17:14
 */
@Component
@ConfigurationProperties(prefix = "zookeeper")
@Data
public class ZkConfig {
    private int baseSleepTimeMs;
    private int maxRetries;
    private String connectString;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;
}
