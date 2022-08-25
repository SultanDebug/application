package com.hzq.algo.greedy;

import com.google.common.collect.Sets;
import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;

import java.util.Iterator;
import java.util.Set;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/11 09:40
 */
public class Dijkstra {
    public static void main(String[] args) {
        //数组型
        arr();
        //树形

    }

    public static void tree(){

    }


    //初始边权值最大值
    public static int max = 10000;

    //最小权值记录数组
    public static int[] dist = new int[]{0,max,max,max,max};

    //最小权值路径前驱记录数组
    public static int[] path = new int[]{0,-1,-1,-1,-1};

    //已确定最小权值集合
    public static Set<Integer> S = Sets.newHashSet(1);
    //未明确最小权值节点集合
    public static Set<Integer> VS = Sets.newHashSet(2,3,4,5);


    //有向带权图
    public static int[][] map = new int[][]{
            {0 , 1,2,max,max},
            {max,0,3,max,4},
            {max,max,0,5,6},
            {max,max,max,0,7},
            {1,max,max,max,0},
    };

    /**
     * Description:
     * 求单源无负权到任意节点最短路径问题
     * 1.经过s中点到vs点  更新最短距离
     * 2.找到最短距离的点
     * 3.加入s，移除vs
     * @author Huangzq
     * @date 2022/8/13 15:23
     */
    public static  void arr(){
        int cur = 1;

        //每次找到的最小节点编码
        Integer minNode = 1;
        while(!VS.isEmpty()){
            //每次找到的最小节点权值大小
            int minDist = max;

            for (Integer v : VS) {
                //当前最小节点到当前节点权值
                int cur2v = map[cur - 1][v - 1];
                if (cur2v == max) {
                    continue;
                }
                //当前节点总权值
                int tol = cur2v + dist[cur - 1];
                //更新路径前驱及最小路径
                if (tol < dist[v - 1]) {
                    path[v-1] = cur;
                    dist[v - 1] = tol;
                }

                //记录未明确集合最小节点编码及最小权值
                if (cur2v < minDist) {
                    minDist = cur2v;
                    minNode = v;
                }
            }

            //最小节点进入明确集合，移除未明确集合
            S.add(minNode);
            VS.remove(minNode);
            cur = minNode;

        }

        System.out.println(JSONUtil.toJsonStr(dist));

        System.out.println(JSONUtil.toJsonStr(path));
    }
}
