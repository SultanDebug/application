package com.hzq.demoservice.test.aop.annotation;

import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author Huangzq
 * @title: ServiceAop
 * @projectName applications
 * @date 2019/8/7 17:27
 */
@Component
public class ServiceAop {
    @MethodTimeCalculate(key = "测试信息")
    public void targetMethod(String name){
        try {
            TimeUnit.SECONDS.sleep(3L);
            System.out.println(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
