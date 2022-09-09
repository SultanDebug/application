package com.hzq.algo.dp;

import com.google.common.collect.Lists;

import java.util.ArrayList;

/**
 * 编辑距离
 * @author Huangzq
 * @description
 * @date 2022/9/2 17:52
 */
public class EditDist {
    public static void main(String[] args) {
        String s = "aaabcaa";

        String aa = s.replaceAll("aa", "**");
        String bb = s.replaceAll("a{2}", "**");

        System.out.println(aa);
        System.out.println(bb);


        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        System.out.println(integers.subList(0,integers.size()));
    }
}
