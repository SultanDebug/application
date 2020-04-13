package com.hzq.netty.leetcode;

/**
 * @author Huangzq
 * @title: ReverseWords
 * @projectName 翻转字符串里的单词
 * @date 2020/4/10 11:23
 */
public class ReverseWords {
    public static void main(String[] args) {
        String ss = "the sky is blue";

        System.out.println(reverseWords(ss));
    }

    /**
     * 给定一个字符串，逐个翻转字符串中的每个单词
     * @param s
     * @return
     */
    public static String reverseWords(String s){
        //去头尾空格
        String ss = s.trim();

        char chars [] = ss.toCharArray();

        int i = chars.length-1;
        int j = i;

        String res = "";

        while (i >= 0){
            //定位空格位置
            if(' ' == chars[i] || i == 0){
                if(' ' != chars[j]){
                    //substring[半开半闭) 0位置特殊处理
                    String tmp = i==0 ? ss.substring(i,j+1) : ss.substring(i+1,j+1);
                    res += tmp+" ";
                    j=i;
                }
                //记录每个单词结束位置
                if(i-1>=0 && chars[i-1] != ' '){
                    j = i-1;
                }

            }
            i--;
        }

        return res.trim();
    }

}
