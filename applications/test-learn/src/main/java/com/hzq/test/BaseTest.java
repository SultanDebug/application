package com.hzq.test;

import cn.hutool.core.lang.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Huangzq
 * @description
 * @date 2023/7/22 15:12
 */
public class BaseTest {
    public static void main(String[] args) {
        List<Pair<Integer,Integer>> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(Pair.of(i,i));
        }

        Integer collect = list.stream().map(Pair::getKey).mapToInt(Integer::intValue).sum();

        System.out.println(collect);


    }
}
