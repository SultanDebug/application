package com.hzq.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * 本地缓存，咖啡因库
 *
 * @author Huangzq
 * @description
 * @date 2023/7/22 09:04
 */
public class CacheUtil {
    private static Cache<String, Object> caffeine = Caffeine.newBuilder()
            //cache的初始容量
            .initialCapacity(10)
            //cache最大缓存数
            .maximumSize(10000)
            //设置写缓存后n秒钟过期
            .expireAfterWrite(1, TimeUnit.DAYS)
            .build();

    public static <T> T getOrDefault(Cache<String, Object> caffeine,String key, Function<String, T> function) {
        return (T) caffeine.get(key, function);
    }

    public static <T> T getOrDefault(String key, Function<String, T> function) {
        return (T) caffeine.get(key, function);
    }

    public static <T> T getIfPresent(String key, Class<T> clazz) {
        return (T) caffeine.getIfPresent(key);
    }

    public static <T> void put(Cache<String, Object> caffeine,String key, T val) {
        caffeine.put(key, val);
    }

    public static <T> void put(String key, T val) {
        caffeine.put(key, val);
    }

    public static void clear(){
        caffeine.invalidateAll();
    }

    public static void clear(Cache<String, Object> caffeine){
        caffeine.invalidateAll();
    }
}
