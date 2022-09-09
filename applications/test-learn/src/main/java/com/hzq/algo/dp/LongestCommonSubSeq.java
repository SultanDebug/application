package com.hzq.algo.dp;

import java.util.Arrays;

/**
 * 最长公共子序列
 * @author Huangzq
 * @description
 * @date 2022/9/2 18:07
 */
public class LongestCommonSubSeq {
    public static void main(String[] args) {
        String s1 = "abcde";
        String s2 = "ace";

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        StringBuilder sb = new StringBuilder();

        int[][] dp = new int[c1.length][c2.length];

        System.out.println(dp(sb,0,0,dp,c1,c2));
        System.out.println(sb.toString());

    }



    public static int dp(StringBuilder sb ,int i , int j,int[][] dp,char[] c1 , char[] c2){
        if(c1.length==0 || c2.length==0){
            return 0;
        }

        if(c1[0] == c2[0]){
            sb.append(c1[0]);
            dp[i][j]=1;
            return 1+dp(sb ,i+1,j+1,dp,Arrays.copyOfRange(c1,1,c1.length),Arrays.copyOfRange(c2,1,c2.length));
        }else{
            int i1 = dp(sb ,i,j+1,dp,Arrays.copyOfRange(c1,0,c1.length),Arrays.copyOfRange(c2,1,c2.length));
            int i2 = dp(sb ,i+1,j, dp,Arrays.copyOfRange(c1,1,c1.length),Arrays.copyOfRange(c2,0,c2.length));
            if(i1>i2){
                dp[i][j+1] = 2;
            }else{
                dp[i+1][j] = 3;
            }
            return Math.max(i1,i2);
        }
    }
}
