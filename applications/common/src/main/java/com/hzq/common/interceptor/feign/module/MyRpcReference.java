package com.hzq.common.interceptor.feign.module;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface MyRpcReference {
    String host() default "127.0.0.1";
    String port() default "8080";
}
