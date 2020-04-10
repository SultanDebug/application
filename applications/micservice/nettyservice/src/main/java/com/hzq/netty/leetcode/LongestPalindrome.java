package com.hzq.netty.leetcode;

/**
 * @author Huangzq
 * @title: LongestPalindrome
 * @projectName  最长回文子串
 * @date 2020/4/10 16:23
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("cbbd"));
    }

    public static String longestPalindrome(String s){
        char dp [] = s.toCharArray();

        int r = 0, l = 0;

        String res = "";

        if(s.length()<2){res = s;}

        res = s.substring(0,1);

        for (int i = 0; i < dp.length; i++) {
            r=l=i;

            for(int j = 1 ; j<dp.length;j++){
                if (r+j<dp.length
                        && l-j>=0
                        &&isDp(l-j,r+j,dp)
                        &&res.length()<r+j-l+j) {
                    res = s.substring(l-j,r+j+1);
                }else{
                    break;
                }
            }

            l = i;r=i+1;

            for(int j = 0 ; j<dp.length;j++){
                if (r+j<dp.length
                        && l-j>=0
                        &&isDp(l-j,r+j,dp)
                        &&res.length()<r+j-l+j+1) {
                    res = s.substring(l-j,r+j+1);
                }else{
                    break;
                }
            }

        }

        return res;
    }

    public static boolean isDp(int l , int r , char[]dp){
        return dp[l] == dp[r];
    }
}
