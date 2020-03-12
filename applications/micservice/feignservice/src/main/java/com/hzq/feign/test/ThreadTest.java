package com.hzq.feign.test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-08
 */
public class ThreadTest {

    public static synchronized void f1(String s){
        System.out.println("线程"+s+"开始");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+s+"结束");
    }

    public synchronized void f2(String s){
        System.out.println("线程"+s+"开始");

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程"+s+"结束");
    }

    public static void main(String[] args) {
//        U.compareAndSwapInt()
        ConcurrentHashMap<String,String> map = new ConcurrentHashMap<>();

        map.put("test","hzq");

        System.out.println(map.get("test"));
        /*UserService userService = new UserService();

        Thread thread1 = new Thread(() -> {
            userService.f1("f1");
        });

        Thread thread2 = new Thread(() -> {
            userService.f2("f2");
        });

        thread1.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();*/

    }
}
