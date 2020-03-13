package com.hzq.netty.handspring;

/**
 * @author Huangzq
 * @title: HandlerOne
 * @projectName applications
 * @date 2020/3/13 10:36
 */
@MyAnnotation(type = "two")
public class HandlerTwo {
    public void sayTwo(String s){
        System.out.println("two : " + s);
    }
}
