package com.hzq.netty.aopcondition.autoconfiguration.config;

import java.lang.annotation.*;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-04-07
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BeanService {
    boolean isRegist() default true;
}
