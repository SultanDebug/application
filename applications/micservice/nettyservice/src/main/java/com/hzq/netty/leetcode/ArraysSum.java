package com.hzq.netty.leetcode;

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

    public static void main(String[] args) {
        /*ArraysSum arraysSum = new ArraysSum();
        int[] s = new int[]{2,7,11,15};
        System.out.println(Arrays.toString(arraysSum.twoSum(s, 9)));*/

        //leetcode 签名行
        for (int i = 1; i <= 80; i++) {
            String s = "";
            for (int j = 0;j<80-i;j++){
                s+=" ";
            }
            s=s+"\uD83D\uDE0D";
            for (int j = 0;j<i;j++){
                s+=" ";
            }
            System.out.println(s);
        }

    }
}
