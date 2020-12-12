package com.hzq.netty.leetcode.arrays;

import java.util.Arrays;

/**
 * @author Huangzq
 * @title: LongestPalindrome
 * @projectName  最长回文子串
 * @date 2020/4/10 16:23
 */
public class LongestPalindrome {
    public static void main(String[] args) {
//        System.out.println(longestPalindrome("abb"));
        System.out.println(isPalindrome(12));
    }

    /**
     * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000
     * @param s
     * @return
     */
    public static String longestPalindrome(String s){
        if(s==null||s.equals("")){
            return s;
        }
        char dp [] = s.toCharArray();

        String res = "";

        char tmp [] = {};

        boolean flag = false;

        for (int i = 0; i < dp.length; i++) {
            for (int j = dp.length-1; j >= i ; j--) {
                //检查i-j
                if(isDp(i,j,dp)){
                    if(tmp.length < j-i+1){
                        tmp = Arrays.copyOfRange(dp,i,j+1);

                        //检查 i+
                        char[] search = search(i, j, dp, i + dp.length - (j+1));
                        if(search.length>tmp.length){
                            flag = false;
                        }else{
                            flag = true;
                        }
                        break;
                    }
                }

            }
            if(flag){
                break;
            }
        }

        return new String(tmp);
    }

    public static char[] search(int l , int r , char[]dp,int le){
        char[] check = {};
        if(le<1){
            return check;
        }
        for (l=l+1; l <= le; l++) {
            for (int j = dp.length-1; j >= l ; j--) {
                //检查i-j
                if(isDp(l,j,dp)){
                    if(check.length < j-l+1){
                        check = Arrays.copyOfRange(dp,l,j+1);
                        break;
                    }
                }
            }
        }
        return check;
    }

    public static boolean isPalindrome(int x) {
        String s = String.valueOf(x);
        if(s.length()==1){
            return true;
        }
        char[] chars = s.toCharArray();
        return isDp(0,chars.length-1,chars);
    }

    public static boolean isDp(int l , int r , char[]dp){
        if(l==r){
            return true;
        }
        if(r==l+1){
            return dp[l]==dp[r];
        }
        if(dp[l]!=dp[r]){
            return false;
        }
        return isDp(l+1,r-1,dp);
    }
}
