package com.hzq.algo.leetcode.arrays;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-04-12
 */
public class Intersection {

    public static void main(String[] args) {
        System.out.println(intersection(new int[]{0,0}
        ,new int[]{3,3}
        ,new int[]{1,1}
        ,new int[]{2,2}));
    }

    /**
     * 给定两条线段（表示为起点start = {X1, Y1}和终点end = {X2, Y2}），如果它们有交点，请计算其交点，没有交点则返回空值。
     *
     * 要求浮点型误差不超过10^-6。若有多个交点（线段重叠）则返回 X 值最小的点，X 坐标相同则返回 Y 值最小的点
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */
    public static double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {

        double k1 = (end1[1] - start1[1]) / (end1[0] - start1[0]);
        double k2 = (end2[1] - start2[1]) / (end2[0] - start2[0]);

        double b1 = start1[1]-k1*start1[0];
        double b2 = start2[1]-k2*start2[0];

        double x = (b1-b2)/(k2-k1);
        double y = k1*x+b1;

        System.out.println(k1+"/"+b1+"/"+k2+"/"+b2+"/"+x+"/"+y);

        return null;
    }
}
