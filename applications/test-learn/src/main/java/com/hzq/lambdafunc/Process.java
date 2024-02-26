package com.hzq.lambdafunc;

/**
 * @author Huangzq
 * @description
 * @date 2024/2/21 09:46
 */

interface ProcessInterface {
    void myProcess(String param);
}

@FunctionalInterface
public interface Process {
    void process(String param);
}

class ProcessOne implements ProcessInterface {
    @Override
    public void myProcess(String param) {
        System.out.println("one==>" + param);
    }
}

class ProcessTwo implements ProcessInterface {
    @Override
    public void myProcess(String param) {
        System.out.println("two==>" + param);
    }
}
