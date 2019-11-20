package com.hzq.demoservice.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Huangzq
 * @title: ZkCon
 * @projectName applications
 * @date 2019/7/4 17:19
 */
@Configuration
public class ZkCon {
    @Autowired
    private ZkConfig zkConfig;

    @Bean
    public CuratorFramework curatorFramework(){
        //重试策略
        RetryPolicy policy = new ExponentialBackoffRetry(zkConfig.getBaseSleepTimeMs(),zkConfig.getMaxRetries());

        //创建curatorFramework

        CuratorFramework curatorFramework =
                CuratorFrameworkFactory.builder()
                .connectString(zkConfig.getConnectString())
                .sessionTimeoutMs(zkConfig.getSessionTimeoutMs())
                .connectionTimeoutMs(zkConfig.getConnectionTimeoutMs())
                .retryPolicy(policy)
                .build();


        //连接zk

        curatorFramework.start();

        //返回连接实例

        return curatorFramework;

    }
}
