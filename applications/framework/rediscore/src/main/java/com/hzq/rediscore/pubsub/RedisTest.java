package com.hzq.rediscore.pubsub;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 发布订阅测试
 * @author Huangzq
 * @title: RedisTest
 * @projectName applications
 * @date 2019/12/4 9:38
 */
public class RedisTest {
    //todo main
    public static void test(String[] args) {
        // 连接redis服务端
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "192.168.50.50", 6379,10,"123456");

        Publisher publisher = new Publisher(jedisPool);    //发布者
        publisher.start();

        Subscriber subscriber = new Subscriber(jedisPool);    //订阅者
        subscriber.start();

        Subscriber subscriber1 = new Subscriber(jedisPool);    //订阅者
        subscriber1.start();
    }
}
