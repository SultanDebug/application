package com.hzq.lambdafunc;

/**
 * @author Huangzq
 * @description
 * @date 2024/2/21 09:53
 */
public class LambdaTest {
    public static void main(String[] args) {
        ProcessOne one = new ProcessOne();
        ProcessTwo two = new ProcessTwo();

        /*方法引用不校验方法名称，只校验返回值和参数类型、顺序、个数*/
        test("param1", one::myProcess);
        test("param2", two::myProcess);
    }

    public static void test(String param, Process process) {
        process.process(param);
    }
}
