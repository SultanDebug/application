package com.hzq.algo.dp;

/**
 * 最长公共子序列
 *
 * @author Huangzq
 * @description
 * @date 2022/9/2 18:07
 */
public class LongestCommonSubSeq {
    public static void main(String[] args) {
        String s1 = "abcdef";
        String s2 = "acaaaf";

        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();

        int[][] dp = new int[c1.length][c2.length];
        int dp1 = dp(0, 0, dp, c1, c2);
        int i = 0;
        int j = 0;
        while (i < c1.length && j < c2.length) {
            if (dp[i][j] == 1) {
                System.out.println(c1[i]);
                i++;
                j++;
            } else if (dp[i][j] == 2) {
                j++;
            } else if (dp[i][j] == 3) {
                i++;
            } else {
                System.out.println("error");
            }
        }

        System.out.println(dp1);

    }

    public static int dp(int i, int j, int[][] dp, char[] c1, char[] c2) {
        if (c1.length == i || c2.length == j) {
            return 0;
        }

        if (c1[i] == c2[j]) {
            dp[i][j] = 1;
            return 1 + dp(i + 1, j + 1, dp, c1, c2);
        } else {
            int i1 = dp(i, j + 1, dp, c1, c2);
            int i2 = dp(i + 1, j, dp, c1, c2);
            if (i1 > i2) {
                dp[i][j] = 2;
            } else {
                dp[i][j] = 3;
            }
            return Math.max(i1, i2);
        }
    }
}
