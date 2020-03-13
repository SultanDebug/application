package com.hzq.netty.aopcondition.function;

/**
 * @author Huangzq
 * @title: FunctionTestInterface
 * @projectName applications
 * @date 2019/12/13 19:34
 */
@FunctionalInterface
public interface FunctionTestInterface {
    int add(int a , int b);

    default int addPlus(int a , int b){
        return a*b;
    }
}
