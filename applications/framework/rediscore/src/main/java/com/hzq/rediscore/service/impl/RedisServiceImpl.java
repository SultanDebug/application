package com.hzq.rediscore.service.impl;

import com.hzq.rediscore.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

/**
 * 基础api
 * @author Huangzq
 * @title: RedisServiceImpl
 * @projectName applications
 * @date 2019/12/4 11:19
 */
@Service
@Slf4j
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public boolean setNx(String key, String val) {
        try {
            boolean rs = redisTemplate.opsForValue().setIfAbsent(key,val);
            return rs;
        }catch (Exception e){
            log.error("竞争异常：{}",e.getMessage());
            return false;
        }
    }

    @Override
    public boolean set(String key, String val) {
        try {
            redisTemplate.opsForValue().set(key,val);
            return true;
        }catch (Exception e){
            log.error("竞争异常：{}",e.getMessage());
            return false;
        }
    }

    @Override
    public boolean set(String key, String val, Long expire) {
        try {
            redisTemplate.opsForValue().set(key,val,expire, TimeUnit.SECONDS);
            return true;
        }catch (Exception e){
            log.error("竞争异常：{}",e.getMessage());
            return false;
        }

    }

    @Override
    public void delete(String key) {
        if(!StringUtils.isEmpty(key)){
            redisTemplate.delete(key);
        }
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public Object getAndSet(String key, String val) {
        return redisTemplate.opsForValue().getAndSet(key,val);
    }
}
