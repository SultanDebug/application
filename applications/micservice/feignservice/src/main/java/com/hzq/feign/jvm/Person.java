package com.hzq.feign.jvm;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-10
 */
public class Person {
    private String name = "hzq";
    private int age = 18;
    private final double salary = 100;
    private static String addess;
    private final static String id = "sultan";

    public void say(){
        System.out.println("hello world");
    }

    public static int cal(int a ,int b){
        a = 3;
        int res = a+b;

        return res;
    }

    public static void main(String[] args) {
        System.out.println(cal(2,5));
    }
}
