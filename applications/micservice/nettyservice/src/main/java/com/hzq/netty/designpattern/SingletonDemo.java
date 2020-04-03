package com.hzq.netty.designpattern;

/**
 * @author Huangzq
 * @title: SingletonDemo
 * @projectName applications
 * @date 2020/4/2 16:03
 */
public class SingletonDemo {

    //饿汉式
    private static SingletonDemo singletonDemo = new SingletonDemo();
    private SingletonDemo(){
        System.out.println("初始化");
    }
    public static SingletonDemo getInstance(){
        return singletonDemo;
    }

    //懒汉式
    private static volatile SingletonDemo singletonDemoH = null;
    public static synchronized SingletonDemo getInstanceH(){
        if(singletonDemoH == null){
            singletonDemoH = new SingletonDemo();
        }

        return singletonDemoH;
    }

    public void option(){
        System.out.println("操作方法");
    }

    public static void main(String[] args) {
        SingletonDemo.getInstanceH().option();
    }
}
