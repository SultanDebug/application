package com.hzq.algo.greedy;

import java.util.Arrays;

/**
 * 古董问题
 * 已知古董重量，求固定运载量能运载的最大古董数量
 * @author Huangzq
 * @description
 * @date 2022/8/10 20:05
 */
public class Treasure {
    public static void main(String[] args) {
        int[] ws = new int[]{2,1,3};
        System.out.println(mostCount(ws,6));
    }

    public static int mostCount(int[] ws , int max ){
        Arrays.sort(ws);
        int tmp = 0;
        int res = 0;

        for (int w : ws) {
            if (tmp + w > max) {
                break;
            }
            res += 1;
            tmp += w;
        }
        return res;
    }
}
