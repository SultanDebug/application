package com.hzq.netty.leetcode.arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Huangzq
 * @title: ArraysSum
 * @projectName applications
 * @date 2020/4/13 14:51
 */
public class ArraysSum {
    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     *
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素
     *
     * map索引
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {

        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            Integer obj = map.get(target - nums[i]);

            if(obj!= null){
                return new int[]{obj,i};
            }

            map.put(nums[i],i);
        }

        return null;
    }

    /**
     * 面试题56 - I. 数组中数字出现的次数
     * 一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。
     * @param nums
     * @return
     */
    public int[] singleNumbers(int[] nums) {

        return null;
    }

    /**
     * 136. 只出现一次的数字1
     * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素
     * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
     *
     * 位运算 异或运算判重和多次异或互反
     *
     * @param nums
     * @return
     */
    public static int singleNumber(int[] nums) {
        int res = 0;

        for (int i = 0; i < nums.length; i++) {
            res = res ^ nums[i];
        }

        return res;
    }

    /**
     * 268. 缺失数字
     * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
     *
     * 位运算 异或运算判重和多次异或互反
     *
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        int tmp = 0;

        for (int i = 0; i < nums.length; i++) {
            tmp = tmp^nums[i];
        }

        int res = 0;
        for (int i = 0; i < nums.length + 1; i++) {
            res = res^i;
        }

        return res^tmp;
    }

    /**
     * 35. 搜索插入位置
     * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
     *
     * 你可以假设数组中无重复元素
     * @param nums
     * @param target
     * @return
     */
    public static int searchInsert(int[] nums, int target) {
        if(nums == null || nums.length==0){
            return 0;
        }
        if(nums.length==1){
            return 1;
        }

        int left = 0;
        int right = nums.length-1 ;

        while (left<right){
            int mid = left+(right-left)/2;
            if(nums[mid]>target){
                right=mid;
            }else{
                left = mid+1;
            }
        }

        return left;

        /*int mid = nums.length>>1;
        if(nums==null || nums.length==0){
            return 0;
        }
        if(nums[mid] <= target){
            for (int i = mid; i < nums.length; i++) {
                if(nums[i]>=target){
                    return i;
                }
            }
        }else{
            for (int i = 0; i < mid+1; i++) {
                if(nums[i]>=target){
                    return i;
                }
            }
        }

        return nums.length;*/
    }

    public int firstBadVersion(int n) {



//        int mid = n>>1;
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }

        int left = 1;
        int right = n ;

        while (left<right){
            int mid = left+(right-left)/2;
            if(isBadVersion(mid)){
               right=mid;
            }else{
                left = mid+1;
            }
        }

        /*if(!isBadVersion(mid)){
            for (int i = mid; i <= n; i++) {
                if(isBadVersion(i)){
                    return i;
                }
            }
        }else{
            for (int i = 0; i < mid+1; i++) {
                if(isBadVersion(i)){
                    return i;
                }
            }
        }*/

        return left;
    }

    boolean isBadVersion(int i){
        return false;
    }

    public static void main(String[] args) {
        /*ArraysSum arraysSum = new ArraysSum();
        int[] s = new int[]{2,7,11,15};
        System.out.println(Arrays.toString(arraysSum.twoSum(s, 9)));*/

        //leetcode 签名行
        /*for (int i = 1; i <= 80; i++) {
            String s = "";
            for (int j = 0;j<80-i;j++){
                s+=" ";
            }
            s=s+"\uD83D\uDE0D";
            for (int j = 0;j<i;j++){
                s+=" ";
            }
            System.out.println(s);
        }*/

        int a[] = {0,1,3,5};


        System.out.println(searchInsert(a,0));

    }
}
