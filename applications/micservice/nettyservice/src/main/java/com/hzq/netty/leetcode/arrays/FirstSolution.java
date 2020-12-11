package com.hzq.netty.leetcode.arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huangzq
 * @title: FirstSolution
 * @projectName applications
 * @date 2020/4/10 11:18
 */
public class FirstSolution {
    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
     * @param s
     * @return
     */
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

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     *
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     *
     * 你可以假设 nums1 和 nums2 不会同时为空
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int i = 0;
        int j = 0;

        double res = 0;

        //获取中位
        boolean flag = (nums1.length+nums2.length) % 2 == 0 ? true : false;
        int middle = flag ? ((nums1.length+nums2.length) / 2)-1 : (nums1.length+nums2.length) / 2;

        int all = 0;

        //边界处理
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

        //遍历归并数组
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

    public static void main(String[] args) {
        //最长子串
        System.out.println(lengthOfLongestSubstring("abcabcbb"));

        //中位数
        int[] a = new int[]{1,3};
        int[] b = new int[]{2,4};
        System.out.println(findMedianSortedArrays(a,b));

        //最长回文子串
//        System.out.println(LongestPalindrome("abcda"));
    }
}
