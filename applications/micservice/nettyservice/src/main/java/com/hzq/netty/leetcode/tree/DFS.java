/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.netty.leetcode.tree;

import java.util.Date;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/12/17 15:11
 */
public class DFS {
    public static int climbStairs(int n) {
//        return dp(n);
        return recursion(0,n);
    }

    /**
     * 70. 爬楼梯
     * */
    public static int dp(int n){
        if(n==0||n==1){
            return 1;
        }

        int [] a = new int[n+1];
        a[0]=1;
        a[1]=1;

        for (int i = 2; i <= n; i++) {
            a[i] = a[i-1]+a[i-2];
        }

        return a[n];
    }
    /**
     * 70. 爬楼梯
     * */
    public static int recursion(int x,int n){
        int r = 0;
        if(x==n){
            return 1;
        }
        if(x+1<=n){
            r = recursion(x+1,n);
        }
        if(x+2<=n){
            r = r+recursion(x+2,n);
        }
        return r;
    }

    public static void main(String[] args) {
        Long start = (new Date()).getTime();
        System.out.println(dp(44));
        Long end = (new Date()).getTime();
        System.out.println("dp:"+(end-start));

        Long start1 = (new Date()).getTime();
        System.out.println(recursion(0,44));
        Long end1 = (new Date()).getTime();
        System.out.println("recursion:"+(end1-start1));
    }
}
