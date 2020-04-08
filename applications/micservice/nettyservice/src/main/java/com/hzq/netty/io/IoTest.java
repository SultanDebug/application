package com.hzq.netty.io;

/**
 * @author Huangzq
 * @title: IoTest
 * @projectName applications
 * @date 2020/4/8 10:27
 */
public class IoTest {

    /*public static class TestLoad{
        static {
            System.out.println("静态块执行");
        }
    }*/

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        ClassLoader classLoader = IoTest.class.getClassLoader();
        System.out.println(classLoader);

//        Class<?> aClass = classLoader.loadClass(TestLoad.class.getName());
//
//        TestLoad o = (TestLoad) aClass.newInstance();
//
//        System.out.println(o.a+"/"+TestLoad.b+"/"+TestLoad.c);

        Class<?> test2 = Class.forName("com.hzq.netty.io.TestLoad");

//        TestLoad o1 = (TestLoad) test2.newInstance();

//        System.out.println(o1.a+"/"+TestLoad.b+"/"+TestLoad.c);

//        Class<?> test21 = Class.forName("com.hzq.netty.io.TestLoad", false, classLoader);

//        TestLoad o2 = (TestLoad) test21.newInstance();
//
//        System.out.println(o2.a+"/"+TestLoad.b+"/"+TestLoad.c);
    }
}
