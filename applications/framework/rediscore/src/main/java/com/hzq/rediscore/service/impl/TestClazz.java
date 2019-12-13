package com.hzq.rediscore.service.impl;

import org.apache.commons.lang.StringUtils;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huangzq
 * @title: TestClazz
 * @projectName applications
 * @date 2019/12/5 16:33
 */
public class TestClazz {
    static abstract class Human{

    }

    static class Man extends Human{}

    static  class Woman extends Human{}

    public void sayHello(Human human){
        System.out.println("hello,guy!");
    }

    public void sayHello(Man man){
        System.out.println("hello,gentleman!");
    }

    public void sayHello(Woman woman){
        System.out.println("hello,lady!");
    }

    public static void init(){
        Human man = new Man();
        Human woman = new Woman();

        TestClazz staticDispatch = new TestClazz();
        staticDispatch.sayHello(man);
        staticDispatch.sayHello(woman);
    }

    public static int lengthOfLongestSubstring(String s) {

        char[] strings = s.toCharArray();
        if(strings.length==1){
            return 1;
        }

        int i = 0;

        List<Character> list = new ArrayList<>();

        String substr = "";
        int result = 0;

        Map<Character,Integer> map = new HashMap<>();

        for(;i<=strings.length;i++){
            if(i>=strings.length){
                if(list.size()>result){
                    result = list.size();
                }
                break;
            }
            Integer old = map.get(strings[i]);
            if(old == null){
                list.add(strings[i]) ;
                map.put(strings[i],i);
            }else{
                if(list.size()>result){
                    result = list.size();
                }
                i = old;
                list.clear();
                map.clear();
            }
        }
        return result;
    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int i = 0;
        int j = 0;

        double res = 0;

        boolean flag = (nums1.length+nums2.length) % 2 == 0 ? true : false;
        int middle = flag ? ((nums1.length+nums2.length) / 2)-1 : (nums1.length+nums2.length) / 2;

        int all = 0;

        if(nums1.length == 0 && nums2.length==1){
            return (nums2[0])/1.0;
        }

        if(nums1.length == 1 && nums2.length==0){
            return (nums1[0])/1.0;
        }

        if(nums1.length == 1 && nums2.length==1){
            return (nums1[0]+nums2[0])/2.0;
        }

        int[] result = new int[nums1.length+nums2.length];

        while(true){
            if(i<nums1.length && j<nums2.length ){
                if(nums1[i]<nums2[j]){
                    result[all] = nums1[i];
                    i++;
                }else{
                    result[all] = nums2[j];
                    j++;
                }

            }else if(i>=nums1.length && j<nums2.length ){
                result[all] = nums2[j];
                j++;
            }else if(i<nums1.length && j>=nums2.length ){
                result[all] = nums1[i];
                i++;
            }else{
                break;
            }
            all++;
        }

        if(flag){
            res = (result[middle]+result[middle+1])/2.0;
        }else{
            res = result[middle]/1.0;
        }

        return res;
    }

    public static String longestPalindrome(String s) {

        String result = "";

        return result;
    }

    public static void test(String[] args) {
        //类初始化
//        init();

        //最长子串
//        System.out.println(lengthOfLongestSubstring("abcd"));

        //中位数
//        int[] a = new int[]{1,3};
//        int[] b = new int[]{2,4};
//        System.out.println(findMedianSortedArrays(a,b));

        //最长回文子串
        System.out.println(longestPalindrome("abcda"));

    }
}
