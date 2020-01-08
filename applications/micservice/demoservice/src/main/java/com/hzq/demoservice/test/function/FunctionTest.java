package com.hzq.demoservice.test.function;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Huangzq
 * @title: FunctionTest
 * @projectName applications
 * @date 2019/12/13 19:36
 */
public class FunctionTest {

    public String getRes(int a , int b , FunctionTestInterface functionTestInterface){
        String s = a+" + "+b+" = "+functionTestInterface.add(a,b);
        return s;
    }

    public static void main(String[] args) {

        FunctionTest functionTest = new FunctionTest();

        FunctionTestInterface functionTestInterface = (a, b) -> {
            return a+b;
        };

        System.out.println(functionTest.getRes(2,4,(a, b) -> a+b));

        System.out.println(functionTestInterface.addPlus(2,4));



    }
}
