package com.hzq.netty;

import com.hzq.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;

/**
 * netty学习
 * 基于netty中间件：tomcat rpc zk leader选举，分布式锁，每次监听最小key值 rocketmq分布式事务
 * leetcode
 * java高级学习 条件注解 jvm io 代理 序列化反序列化
 *
 */
@EnableApplication
public class NettyApp
{
    public static void main( String[] args )
    {

        SpringApplication.run(NettyApp.class, args);
    }
}
