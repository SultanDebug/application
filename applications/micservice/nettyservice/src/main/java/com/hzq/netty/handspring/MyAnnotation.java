package com.hzq.netty.handspring;

import java.lang.annotation.*;

/**
 * @author Huangzq
 * @title: MyAnnotation
 * @projectName applications
 * @date 2020/3/13 10:33
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {

    String type() ;
}
