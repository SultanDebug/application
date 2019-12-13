package com.hzq.demoservice.test.function;

/**
 * @author Huangzq
 * @title: FunctionTest
 * @projectName applications
 * @date 2019/12/13 19:36
 */
public class FunctionTest {
    public static void main(String[] args) {

        FunctionTestInterface functionTestInterface = (a, b) -> {
            return a+b;
        };

        System.out.println(functionTestInterface.add(1,4));

    }
}
