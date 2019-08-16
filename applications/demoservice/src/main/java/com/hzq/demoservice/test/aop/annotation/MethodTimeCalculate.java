package com.hzq.demoservice.test.aop.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Huangzq
 * @title: MethodTimeCalculate
 * @projectName applications
 * @date 2019/8/7 17:15
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MethodTimeCalculate {
    String key() default "";
}
