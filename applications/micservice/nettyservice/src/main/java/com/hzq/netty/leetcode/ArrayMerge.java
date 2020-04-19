package com.hzq.netty.leetcode;

import java.io.IOException;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-04-16
 */
public class ArrayMerge {

    public static void main(String[] args) throws IOException {

        ArrayMerge arrayMerge = new ArrayMerge();
//        int[][] a = new int[][] {{3,5},{1,2},{4,6},{7,8}}; //{{1,4},{2,3}};
//        arrayMerge.merge(a);
        int[] a = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(arrayMerge.maxArea1(a));
        System.in.read();
    }

    /**
     * 给出一个区间的集合，请合并所有重叠的区间。
     * @param intervals
     * @return
     */
    public int[][] merge(int[][] intervals) {

        int[][] res = new int[intervals.length][2];

        for (int i = 0; i < intervals.length; i++) {
            int[] tmp = null;
            for (int j = i; j < intervals.length; j++) {
                if(intervals[j][0] < intervals[i][0]){
                    tmp = intervals[i];
                    intervals[i] = intervals[j];
                    intervals[j] = tmp;
                }
                
            }
        }

        int k = 0;

        for (int i = 0; i < intervals.length; ) {
            int tmp = 0;
            int j = i;
            for (; j < intervals.length; j++) {
                if(intervals[i][1] >= intervals[j][0] && intervals[i][1] <= intervals[j][1] ){
//                    tmp = intervals[j][1];
                    intervals[i][1]=intervals[j][1];
                }else if(intervals[i][1] >= intervals[j][0] && intervals[i][1] > intervals[j][1]){

                }else{
                    break;
                }
            }
            if(i == j-1){
                res[k] = intervals[j-1];
            }else{
                res[k] = intervals[i];
            }
            k++;
            i=j;
        }

        int[][] fin = new int[k][2];
        System.arraycopy(res, 0, fin, 0, fin.length);
        return fin;
    }

    /**
     * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
     * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，
     * 使得它们与 x 轴共同构成的容器可以容纳最多的水。
     *
     * 说明：你不能倾斜容器，且 n 的值至少为 2
     *
     * 暴力求解
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            int a = height[i];
            int j = i;

            for (; j < height.length; j++) {
                if(i==j){continue;}
                int min = height[i] < height[j] ? height[i] : height[j];
                int s = min*(j-i);
                if(max < s){
                    max = s;
                }
            }

        }
        return max;
    }

    /**
     * 双指针法
     * @param height
     * @return
     */
    public int maxArea1(int[] height) {
        int max = 0;
        int left = 0;
        int right = height.length - 1;
        while(true){
            if(left == right){
                break;
            }
            int min = height[left] < height[right] ? height[left] : height[right];
            int s = min*(right-left);

            if(max < s){max = s;}

            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }

        }
        return max;
    }
}
