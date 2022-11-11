package com.hzq.algo.dp;

/**
 * 编辑距离
 *
 * @author Huangzq
 * @description
 * @date 2022/9/2 17:52
 */
public class EditDist {
    public static void main(String[] args) {
        String s1 = "abcdef";
        String s2 = "acaaaf";

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int[][] dp = new int[c1.length][c2.length];

        int[][] path = new int[c1.length][c2.length];

        for (int i = 0; i < c1.length; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i < c2.length; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < c1.length; i++) {
            for (int j = 1; j < c2.length; j++) {
                int del = dp[i-1][j] + 1;
                int ins = dp[i][j-1] + 1;
                int update = c1[i] == c2[j] ? dp[i-1][j-1] : dp[i-1][j-1]+1;

                dp[i][j] = Math.min(Math.min(del,ins),update);
            }
        }

        /**
         *
         */
        for (int i = 1; i < c1.length; i++) {
            for (int j = 1; j < c2.length; j++) {

                if(c1[i] == c2[j]){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    if(i==j){
                        dp[i][j] = dp[i-1][j-1]+1;
                    }else if(i<j){
                        dp[i][j] = dp[i][j-1]+1;
                    }else{
                        dp[i][j] = dp[i-1][j]+1;
                    }
                }
            }
        }

        System.out.println(dp);

    }
}
