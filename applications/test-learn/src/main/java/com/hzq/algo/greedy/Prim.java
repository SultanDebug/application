package com.hzq.algo.greedy;

import com.google.common.collect.Lists;
import com.xiaoleilu.hutool.json.JSONUtil;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/16 14:50
 */
public class Prim {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /*Prim prim = new Prim();
        prim.prim1();

        System.out.println(JSONUtil.toJsonStr(prim.path));
        System.out.println(JSONUtil.toJsonStr(prim.dist));*/

        Prim prim = new Prim();
        prim.prim2();

        System.out.println(JSONUtil.toJsonStr(prim.path1));
        System.out.println(JSONUtil.toJsonStr(prim.dist1));
    }

    int max = 10000;

    public int[][] map = new int[][]{
            {max, 2, max, max, max, 4, 4},
            {2, max, 6, max, max, max, 3},
            {max, 6, max, 1, max, max, 5},
            {max, max, 1, max, 3, max, 2},
            {max, max, max, 3, max, 2, 6},
            {max, max, max, max, 2, max, 5},
            {4, 3, 5, 2, 6, 5, max}
    };

    List<Integer>[] path = new List[]{Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList()};

    List<Integer>[] dist = new List[]{Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList(), Lists.newArrayList()};


    List<Integer> v = Lists.newArrayList(0);
    List<Integer> vs = Lists.newArrayList(1, 2, 3, 4, 5, 6);

    int[] path1 = new int[7];
    int[] dist1 = new int[7];
    /**
     * Description:
     *  最小生成树
     *  1.找出v节点j到vs节点中t，使得map[j][t]最小权值的一条边，记录路径及距离
     *  2.把t加入v集合，vs移除t节点
     *  3.循环至vs为空，结束算法，得到的path1为最小生成树
     * @param
     * @return
     * @author Huangzq
     * @date 2022/8/25 15:16
     */
    public void prim2(){
        path1[0] = 0;
        dist1[0] = 0;
        boolean flag = false;

        while(!vs.isEmpty()){

            int minParent = 0;
            Integer minNode = 0;
            int minDist = max;

            for (Integer v1 : v) {
                for (Integer v2 : vs) {
                    if(map[v1][v2] < minDist){
                        minParent = v1;
                        minNode = v2;
                        minDist = map[v1][v2];
                        flag = true;
                    }
                }
            }

            if(!flag){
                break;
            }

            path1[minNode] = minParent;
            dist1[minNode] = minDist;

            vs.remove(minNode);
            v.add(minNode);

        }
    }

    /**
     * Description:
     *  每次找v到vs的最短节点
     * @param
     * @return
     * @author Huangzq
     * @date 2022/8/24 16:47
     */
    public void prim1() {
        while (!vs.isEmpty()) {
            boolean flag = false;
            int min = max;
            int minV = 0;
            Integer minVs = 0;
            for (Integer x : v) {
                for (Integer y : vs) {
                    if (min > map[x][y]) {
                        minV = x;
                        minVs = y;
                        min = map[x][y];
                        flag = true;
                    }
                }
            }

            if (!flag) {
                throw new RuntimeException("存在不可达路径：" + JSONUtil.toJsonStr(vs));
            }

            v.add(minVs);
            vs.remove(minVs);

            path[minV].add(minVs);

            dist[minV].add(min);
        }
    }

}
