package com.hzq.netty.io;

/**
 * @author Huangzq
 * @title: TestLoad
 * @projectName applications
 * @date 2020/4/8 17:06
 */
public class TestLoad {

    public String a = "hzq";

    public static final Integer b = 3;

    public static String c ;

    static {
        System.out.println("静态块执行");
        c = "sultan";
    }

}
