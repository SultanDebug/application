package com.hzq.demoservice.test.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author Huangzq
 * @title: AspectOpt
 * @projectName applications
 * @date 2019/8/7 9:41
 */
@Component
@Aspect
public class AspectOpt {

    @Before(value = "execution(public void opration(String))")
    public void before(JoinPoint point){
        System.out.println("执行前："+point.toString());
    }

    @AfterReturning(value = "execution(public void opration(String))")
    public void after(JoinPoint point){
        System.out.println("执行后："+point.toString());
    }
}
