package com.hzq.common.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Huangzq
 * @title: EnableApplication
 * @projectName applications
 * @date 2019/11/13 10:05
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({EnableApplicationConfig.class})
public @interface EnableApplication {
}
