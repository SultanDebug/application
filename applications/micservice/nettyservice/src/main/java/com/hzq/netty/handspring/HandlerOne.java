package com.hzq.netty.handspring;

/**
 * @author Huangzq
 * @title: HandlerOne
 * @projectName applications
 * @date 2020/3/13 10:36
 */
@MyAnnotation(type = "one")
public class HandlerOne {
    public void sayOne(String s){
        System.out.println("one : " + s);
    }
}
