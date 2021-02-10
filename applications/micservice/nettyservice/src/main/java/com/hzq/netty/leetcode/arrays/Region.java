/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.netty.leetcode.arrays;

import java.util.*;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/1/25 15:07
 */
public class Region {
    /**
     * 959. 由斜杠划分区域
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/1/25 15:07
    */
    public int regionsBySlashes(String[] grid) {
        return 1;
    }

    /**
     * 424. 替换后的最长重复字符
     * 
     * @param
     * @return 
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/2/2 17:00
    */
    public static int characterReplacement(String s, int k) {
        if(s.length()==0){
            return 0;
        }
        if(s.length()<=k){
            return s.length();
        }
        char [] chars = s.toCharArray();
        int[] his = new int[26];
        //滑动窗口记录历史最大字母次数  固定窗口大小  向右扩张
        //右边不符合条件则不可能向左扩张
        int left = 0 , right = 0 , max = 0;

        for(;right<chars.length;right++){
            int index = chars[right]-'A';
            his[index]++;
            max = Math.max(max,his[index]);
            if(max+k<right-left+1){
                int tmp = chars[left]-'A';
                his[tmp]--;
                left++;
            }
        }
        return s.length()-left;

    }

    /**
     * 46. 全排列
     * 
     * @param
     * @return 
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/2/8 15:24
    */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();


        /*for (int i = 0; i < nums.length; i++) {
            boolean [] booleans = new boolean[nums.length];
            booleans[i] = true;
            Stack<Integer> integers = new Stack<>();
            integers.push(nums[i]);

            booleans[i] = false;
            integers.pop();
        }*/
        boolean [] booleans = new boolean[nums.length];
        Stack<Integer> integers = new Stack<>();
        dfs(res,nums,0,booleans,integers);
        return res;
    }

    public static void dfs(List<List<Integer>> res, int[] nums, int depth,boolean[] booleans, Stack<Integer> stack){
        if(depth==nums.length-1){
            int k = 0;
            for (int i = 0; i < booleans.length; i++) {
                if(!booleans[i]){
                    k = i;
                    stack.push(nums[i]);
                    break;
                }
            }
            List<Integer> integers = new ArrayList<>(stack);
            res.add(integers);
            booleans[k] = false;
            stack.pop();
        }

        for (int i = 0; i < booleans.length; i++) {
            if(!booleans[i]){
                booleans[i] = true;
                stack.push(nums[i]);
                dfs(res,nums,depth+1,booleans,stack);
                booleans[i] = false;
                stack.pop();
            }
        }
    }

    public static int subarraysWithKDistinct(int[] A, int K) {
//        List<int[]> list = new ArrayList<>();
        int res = 0;
        for (int i = 0; i < A.length; i++) {
            Map<Integer,Integer> map = new HashMap<>(K);
            for (int i1 = i; i1 < A.length; i1++) {
                if(map.get(A[i1])==null){
                    if(map.size()<K){
                        map.put(A[i1],A[i1]);
                    }else{
//                        list.add(Arrays.copyOfRange(A,i,i1));
                        res++;
                        break;
                    }
                }else{
                    if(map.size()==K){
//                        list.add(Arrays.copyOfRange(A,i,i1));
                        res++;
                    }
                }
                if(i1==A.length-1&&map.size()==K){
//                    list.add(Arrays.copyOfRange(A,i,A.length));
                    res++;
                }
            }
        }
        return res;
    }



    public static void main(String[] args) {
//        characterReplacement("AABABBAAA",2);
        /*int[] a = new int[]{1,2,3,4};
        System.out.println(permute(a));*/

        int[] a = new int[]{1,2,1,3,4};
        System.out.println(subarraysWithKDistinct(a,3));
    }
}
