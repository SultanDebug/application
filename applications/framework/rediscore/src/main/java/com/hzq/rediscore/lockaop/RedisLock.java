package com.hzq.rediscore.lockaop;

import java.lang.annotation.*;

/**
 * 分布式锁注解方法标记
 * @author Huangzq
 * @title: RedisLock
 * @projectName applications
 * @date 2019/12/4 9:33
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLock {
    String lockKey() ;
    int expire() default 30000;
    int timeout() default 10000;

}
