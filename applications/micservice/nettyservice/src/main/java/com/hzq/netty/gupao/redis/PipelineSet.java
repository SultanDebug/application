package com.hzq.netty.gupao.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-04
 */
public class PipelineSet {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.1.40",6379);
        jedis.auth("123456");
        Pipeline pipelined = jedis.pipelined();

        long a = System.currentTimeMillis();

        for (int i = 0 ; i < 100;i++){
            pipelined.set("key_"+i,i+"");
        }
        List<Object> objects = pipelined.syncAndReturnAll();
        long b = System.currentTimeMillis();

        System.out.println("花费："+(b-a));

    }
}
