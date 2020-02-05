package com.hzq.netty.gupao.redis;

import org.apache.commons.lang.StringUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;

import java.util.List;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-04
 */
public class PipelineGet {
    public static void main(String[] args) {

        pipGet();

        cliGet();
    }

    public static void pipGet(){
        Jedis jedis = new Jedis("192.168.1.40",6379);
        jedis.auth("123456");
        Pipeline pipelined = jedis.pipelined();

        long a = System.currentTimeMillis();

        for (int i = 0 ; i < 100;i++){
            pipelined.get("key_"+i);
        }
        List<Object> objects = pipelined.syncAndReturnAll();
        long b = System.currentTimeMillis();

        System.out.println("花费："+(b-a)+",结果："+ StringUtils.join(objects.toArray(),","));
    }

    public static void cliGet(){
        Jedis jedis = new Jedis("192.168.1.40",6379);
        jedis.auth("123456");

        long a = System.currentTimeMillis();

        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0 ; i < 100;i++){
            stringBuilder.append(jedis.get("key_"+i)).append(",");
        }
        long b = System.currentTimeMillis();

        System.out.println("花费："+(b-a)+",结果："+stringBuilder.toString());
    }
}
