package com.hzq.netty.gupao.redis.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-04
 */
public class LettuceSet {
    public static void main(String[] args) {
        RedisClient redisClient = RedisClient.create("redis://192.168.1.40:6379");
        StatefulRedisConnection<String, String> connect = redisClient.connect();

        RedisCommands<String, String> sync = connect.sync();

        sync.auth("123456");

        sync.set("lettuce:key1","lettuce-666");

        String s = sync.get("lettuce:key1");

        System.out.println("结果："+s);

        connect.close();


    }
}
