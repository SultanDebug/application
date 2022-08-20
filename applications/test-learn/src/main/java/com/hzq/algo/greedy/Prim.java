package com.hzq.algo.greedy;

import com.google.common.collect.Lists;
import com.xiaoleilu.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/16 14:50
 */
public class Prim {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Prim prim = new Prim();
        prim.getPrim();

        System.out.println(JSONUtil.toJsonStr(prim.path));
        System.out.println(JSONUtil.toJsonStr(prim.dist));
    }

    int max = 10000;

    public int[][] map = new int[][]{
            {max, 2, max, max, max, 4, 4},
            {2, max, 6, max, max, max, 3},
            {max, 6, max, 1, max, max, 5},
            {max, max, 1, max, 3, max, 2},
            {max, max, max, 3, max, 2, 6},
            {max, max, max, max, 2, max, 5},
            {4,     3,   5,   2, 6,   5, max}
    };

    List<Integer> [] path = new List []{Lists.newArrayList(),Lists.newArrayList(),Lists.newArrayList(),Lists.newArrayList(),Lists.newArrayList(),Lists.newArrayList(),Lists.newArrayList()};

    List<Integer> [] dist = new List []{Lists.newArrayList(),Lists.newArrayList(),Lists.newArrayList(),Lists.newArrayList(),Lists.newArrayList(),Lists.newArrayList(),Lists.newArrayList()};


    List<Integer> v = Lists.newArrayList(0);
    List<Integer> vs = Lists.newArrayList(1,2,3,4,5,6);

    public void getPrim() {
        while(!vs.isEmpty()){
            boolean flag = false;
            int min = max;
            int minV = 0;
            Integer minVs = 0;
            for (Integer x : v) {
                for (Integer y : vs) {
                    if(min>map[x][y]){
                        minV = x;
                        minVs = y;
                        min = map[x][y];
                        flag = true;
                    }
                }
            }

            if(!flag){
                throw new RuntimeException("存在不可达路径："+ JSONUtil.toJsonStr(vs));
            }

            v.add(minVs);
            vs.remove(minVs);

            path[minV].add(minVs);

            dist[minV].add(min);
        }
    }

}
