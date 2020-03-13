package com.hzq.netty.aopcondition.aop;

import org.springframework.stereotype.Component;

/**
 * @author Huangzq
 * @title: AspectTest
 * @projectName applications
 * @date 2019/8/7 9:37
 */
@Component
public class AspectTest {

    public void opration(String arg){
        System.out.println("opration is :"+arg);
    }

}
