package com.hzq.netty.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Huangzq
 * @title: MatrixOperation
 * @projectName applications
 * @date 2020/4/15 9:51
 */
public class MatrixOperation {
    public static void main(String[] args) {
        int[] nums = {2,4,6};//{1,1,2,1,1}  {2,2,2,1,2,2,1,2,2,2}

        String a = "1010";
        String b = "1011";

//        System.out.println(numberOfSubarrays(nums,1));
        System.out.println(addBinary(a,b));
    }

    /**
     * 542. 01 矩阵
     * 给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。
     *
     * 两个相邻元素间的距离为 1
     * @param matrix
     * @return
     */
    public int[][] updateMatrix(int[][] matrix) {
        return null;
    }

    /**
     * 200. 岛屿数量
     * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
     *
     * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
     *
     * 此外，你可以假设该网格的四条边均被水包围。
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        return 0;
    }

    /**
     * 1248. 统计「优美子数组」
     * 给你一个整数数组 nums 和一个整数 k。
     *
     * 如果某个 连续 子数组中恰好有 k 个奇数数字，我们就认为这个子数组是「优美子数组」。
     *
     * 请返回这个数组中「优美子数组」的数目
     *
     * 只关注奇数窗口开始节点前面的偶数个数和结束节点后面的偶数个数
     *
     * @param nums
     * @param k
     * @return
     */
    public static int numberOfSubarrays(int[] nums, int k) {
        int res = 0;
        List<Integer> singles = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if((nums[i] & 1) == 1){
                singles.add(i);
            }

        }

        if(singles.size()==0 || singles.size()<k){
            return 0;
        }

        int start = 0;
        int end = 0;

        int i = 0;

        while (true){
            start = singles.get(i);
            end = singles.get(i+k-1);

            int ssum = start==0?1:((i-1<0)?(start+1):start-singles.get(i-1));
            int esum = (i+k==singles.size())?(nums.length-end):singles.get(i+k)-end;
            res = res + ssum*esum;
            if(i+k==singles.size()){
                break;
            }

            i++;
        }

        /**
         * 暴力求解
         */
        /*for (int i = 0; i < nums.length; i++) {
            int tmp = 0;
            if(nums[i]%2!=0){
                tmp++;
            }
            for (int j = i; j < nums.length; j++) {

                if(j!=i){
                    if(nums[j]%2!=0){
                        tmp++;
                    }
                    if(tmp==k){
                        res++;
                    }
                    if(tmp>k){
                        break;
                    }
                }else{
                    if(tmp==k){
                        res++;
                    }
                    if(tmp>k){
                        break;
                    }
                }
            }
        }*/
        return res;
    }

    /**
     * 67. 二进制求和
     * 给你两个二进制字符串，返回它们的和（用二进制表示）。
     *
     * 输入为 非空 字符串且只包含数字 1 和 0
     * @param a
     * @param b
     * @return
     */
    public static String addBinary(String a, String b) {
        char[] aa = a.toCharArray();
        char[] bb = b.toCharArray();

        if(a.equals("0")){
            return b;
        }

        if(b.equals("0")){
            return a;
        }

        int ai = aa.length-1;
        int bi = bb.length-1;

        int max = ai>bi?ai:bi;

        int jin = 0;

        String s = "";

        while (true){
            if(ai>=0&&bi>=0){
                if(aa[ai]=='0' && bb[bi]=='0'){
                    if(jin==0){
                        s="0"+s;
                    }else{
                        s="1"+s;
                        jin = 0;
                    }
                }else if(aa[ai]=='1' && bb[bi]=='0' || aa[ai]=='0' && bb[bi]=='1'){
                    if(jin==0){
                        s="1"+s;
                    }else{
                        s="0"+s;
                        jin = 1;
                    }
                }else{
                    if(jin==0){
                        s="0"+s;
                    }else{
                        s="1"+s;

                    }
                    jin = 1;
                }
                ai--;
                bi--;
            }else if (ai>=0&&bi<0 ){
                if(aa[ai] == '0'){
                    if(jin==0){
                        s="0"+s;
                    }else{
                        s="1"+s;
                        jin = 0;
                    }
                }else{
                    if(jin==0){
                        s="1"+s;
                    }else{
                        s="0"+s;
                        jin = 1;
                    }
                }
                ai--;
            }else if (ai<0&&bi>=0){
                if(bb[bi] == '0'){
                    if(jin==0){
                        s="0"+s;
                    }else{
                        s="1"+s;
                        jin = 0;
                    }
                }else{
                    if(jin==0){
                        s="1"+s;
                    }else{
                        s="0"+s;
                        jin = 1;
                    }
                }
                bi--;
            }else{
                break;
            }
        }

        if(jin==1){
            s="1"+s;
        }

        return s;
    }

}
