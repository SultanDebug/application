package com.hzq.cache;

import java.lang.annotation.*;

/**
 * @author Huangzq
 * @description
 * @date 2023/7/21 17:08
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LocalCache {
    /**
     * 缓存前缀
     */
    String prefix();

    /**
     * 缓存超时
     */
    @Deprecated
    long expire() default 1L;
}
