package com.hzq.netty.aopcondition.aop.annotation;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author Huangzq
 * @title: TimeAopProc
 * @projectName applications
 * @date 2019/8/7 17:20
 */
@Component
@Aspect
@Slf4j
public class TimeAopProc {
    @Around("@annotation(MethodTimeCalculate)")
    public Object timeCal(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();

        MethodSignature signature = (MethodSignature) point.getSignature();

        Method method = signature.getMethod();

        MethodTimeCalculate methodTimeCalculate = method.getAnnotation(MethodTimeCalculate.class);

        log.info(methodTimeCalculate.key());

        Object o = point.proceed();
        long end = System.currentTimeMillis();
        log.info(point.getSignature()+" time is : "+(end-start));
        return o;
    }
}
