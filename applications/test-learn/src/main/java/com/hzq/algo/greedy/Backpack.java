package com.hzq.algo.greedy;

import java.util.Arrays;

/**
 * 背包问题
 * 物品 重量，价值  背包 固定重量  求最大价值【物品可切分】
 * 思考：不可切分：0-1背包问题
 * @author Huangzq
 * @description
 * @date 2022/8/10 20:14
 */
public class Backpack {

    class Goods{
        public int w;
        public int v;
        public double vw;

        public Goods(int w, int v) {
            this.w = w;
            this.v = v;
            this.vw = (v*1.0)/w;
        }
    }

    public static void main(String[] args) {
        Backpack backpack = new Backpack();

        Goods[] goods = backpack.getGoods();
        System.out.println(mostCount(goods,3));
    }

    public Goods[] getGoods(){
        return new Goods[]{
                new Backpack.Goods(1,2),
                new Backpack.Goods(2,3),
                new Backpack.Goods(4,5),
        };
    }

    public static int mostCount(Goods[] goods , int max){
        Arrays.sort(goods,(o1,o2)-> Double.compare(o2.vw, o1.vw));
        int tmp = 0;
        int res = 0;
        int val = 0;

        for (Goods good : goods) {
            if (tmp + good.w > max) {
                break;
            }
            res += 1;
            tmp += good.w;
            val += good.v;
        }

        System.out.println(val);
        return res;
    }



}
