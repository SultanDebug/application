package com.hzq.netty.leetcode;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-04-16
 */
public class ArrayMerge {

    public static void main(String[] args) {
        int[][] a = new int[][]{{3,5},{1,2},{4,6},{7,8}}; //[[1,4],[2,3]]
        ArrayMerge arrayMerge = new ArrayMerge();
        arrayMerge.merge(a);
    }

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
                if(intervals[i][1] >= intervals[j][0]){
                    tmp = intervals[j][1];
                    intervals[i][1]=tmp;
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
}
