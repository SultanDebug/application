package com.hzq.algo.dp;

/**
 * 编辑距离
 *   opt：删除 d-花费1 插入 i-花费1 替换 r-花费1
 *   最优子问题：
 *   x[i],y[j] = MIN(
 *   x[i-1],y[j] + 1,【删除】
 *   x[i],y[j-1] + 1,【插入】
 *   x[i-1],y[j-1] + 0/1,【替换】【x[i]==y[j] 无需操作】
 *   )
 *
 *   例：source：ab    target：acd
 *   **************************
 *   *     *   -  *  a  *  b  *
 *   **************************
 *   *  -  *   0  *  1  *  2  *
 *   **************************
 *   *     *      * d:2 * d:3 *
 *   *  a  *   1  * i:2 * i:1 *
 *   *     *      * u:0 * u:2 *
 *   *     *      * r:0 * r:1 *
 *   **************************
 *   *     *      * d:1 * d:2 *
 *   *  c  *   2  * i:3 * i:2 *
 *   *     *      * u:2 * u:1 *
 *   *     *      * r:1 * r:1 *
 *   **************************
 *   *     *      * d:2 * d:2 *
 *   *  d  *   3  * i:4 * i:3 *
 *   *     *      * u:3 * u:2 *
 *   *     *      * r:2 * r:2 *
 *   **************************
 *   [0, 1]
 *   [1, 1]
 *   [2, 2]
 * @author Huangzq
 * @description
 * @date 2022/9/2 17:52
 */
public class EditDist {
    public static void main(String[] args) {
        String s1 = "acd";
        String s2 = "ab";

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
