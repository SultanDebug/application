package com.hzq.rediscore.pubsub;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisTest {
    public static void main(String[] args) {
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
