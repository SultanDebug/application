package com.hzq.test;

import cn.hutool.core.lang.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Huangzq
 * @description
 * @date 2023/7/22 15:12
 */
public class BaseTest {
    public static void main(String[] args) {
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(Pair.of(i, i));
        }

        Integer collect = list.stream().map(Pair::getKey).mapToInt(Integer::intValue).sum();

        System.out.println(collect);

        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        test();
    }

    public static void test(){
        BaseTest baseTest = new BaseTest();
        baseTest.process(BaseTest::process1);
        baseTest.process(BaseTest::process2);
    }

    public void process(Process process){
        System.out.println(process.process("test"));
    }

    public static String process1(String para){
        return "process1:"+para;
    }

    public static String process2(String para){
        return "process2:"+para;
    }

    @FunctionalInterface
    public interface Process{
        String process(String para);
    }
}
