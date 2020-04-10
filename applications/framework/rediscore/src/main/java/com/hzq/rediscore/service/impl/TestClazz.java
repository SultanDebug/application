package com.hzq.rediscore.service.impl;

import org.apache.commons.lang.StringUtils;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huangzq
 * @title: TestClazz
 * @projectName applications
 * @date 2019/12/5 16:33
 */
public class TestClazz {
    static abstract class Human{

    }

    static class Man extends Human{}

    static  class Woman extends Human{}

    public void sayHello(Human human){
        System.out.println("hello,guy!");
    }

    public void sayHello(Man man){
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman woman){
        System.out.println("hello,lady!");
    }

    public static void init(){
        Human man = new Man();
        Human woman = new Woman();

        TestClazz staticDispatch = new TestClazz();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
    }



    public static void test(String[] args) {
        //类初始化
        init();

    }
}
