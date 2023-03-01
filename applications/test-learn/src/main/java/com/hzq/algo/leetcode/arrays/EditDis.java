package com.hzq.algo.leetcode.arrays;

/**
 * @author Huangzq
 * @description
 * @date 2023/3/1 14:00
 */
public class EditDis {

    public static void main(String[] args) {
        System.out.println(minDistance("horse","ros"));
    }

    public static int minDistance(String word1, String word2) {
        if(word1.length()==0){
            return word2.length();
        }
        if(word2.length()==0){
            return word1.length();
        }
        char[] arr1 = word1.toCharArray();
        char[] arr2 = word2.toCharArray();
        int[][] dp = new int[arr1.length+1][arr2.length+1];

        for (int i = 0; i < arr1.length+1; i++) {
            dp[i][0] = i;
        }

        for (int i = 0; i < arr2.length+1; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i < arr1.length+1 ; i++) {
            for (int j = 1; j < arr2.length+1; j++) {
                int up = dp[i-1][j] + 1;
                int left = dp[i][j-1] +1;
                int leftUp = arr1[i-1]!=arr2[j-1] ? dp[i-1][j-1]+1 : dp[i-1][j-1];

                dp[i][j] = Math.min(Math.min(up,left),leftUp);
            }
        }
        return dp[arr1.length][arr2.length];
    }
}
