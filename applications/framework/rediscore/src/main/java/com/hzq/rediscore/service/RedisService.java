package com.hzq.rediscore.service;

/**
 * @author Huangzq
 * @title: RedisService
 * @projectName applications
 * @date 2019/12/4 11:19
 */
public interface RedisService {

    boolean setNx(String key , String val);

    boolean set(String key , String val);

    boolean set(String key , String val ,Long expire);

    void delete(String key);

    Object get(String key);

    Object getAndSet(String key,String val);

}
