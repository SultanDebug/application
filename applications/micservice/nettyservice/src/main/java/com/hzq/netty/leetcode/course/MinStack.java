/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.netty.leetcode.course;

import java.util.LinkedList;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/3/3 16:31
 */
public class MinStack {
    LinkedList<Integer> stack ;
    int min = Integer.MAX_VALUE;
    public MinStack() {
        stack = new LinkedList<>();
    }

    public void push(int x) {
        stack.addLast(x);
        if(min>x){
            min = x;
        }
    }

    public void pop() {
        Integer last = stack.getLast();
        stack.removeLast();
        if(last.equals(min)){
            findMin();
        }
    }

    private void findMin(){
        min = Integer.MAX_VALUE;
        stack.forEach(o->{
            if(o<min){
                min=o;
            }
        });
    }

    public int top() {
        return stack.getLast();
    }

    public int min() {
        return min;
    }
    public String reverseLeftWords(String s, int n) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = n; i < chars.length; i++) {
            stringBuilder.append(chars[i]);
        }
        for (int i = 0; i < n; i++) {
            stringBuilder.append(chars[i]);
        }

        return stringBuilder.toString();
    }

    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length==0||nums.length==1||k==0||k==1){
            return nums;
        }
        int[] res = new int[nums.length-k+1];
        for (int i = 0; i < nums.length; i++) {
            int t = i+k-1;
            int max = nums[i];
            for (int j = i; j <= t; j++) {
                if(nums[j]>max){
                    max = nums[j];
                }
            }
            res[i]=max;
            if(t >= nums.length-1){
                break;
            }
        }
        return res;
    }


    public int[] maxSlidingWindow1(int[] nums, int k) {
        if(nums.length==0||nums.length==1||k==0||k==1){
            return nums;
        }
        int[] res = new int[nums.length-k+1];
        for (int i = 0; i < nums.length; i++) {
            int t = i+k-1;
            int max = nums[i];
            for (int j = i; j <= t; j++) {
                if(nums[j]>max){
                    max = nums[j];
                }
            }
            res[i]=max;
            if(t >= nums.length-1){
                break;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        /*MinStack cQueue = new MinStack();
        cQueue.push(-2);
        cQueue.push(0);
        cQueue.push(-3);
        System.out.println(cQueue.min());
        cQueue.pop();
        System.out.println(cQueue.top());
        System.out.println(cQueue.min());*/
        MinStack cQueue = new MinStack();
//        System.out.println(cQueue.reverseLeftWords("abcde",1));;
        System.out.println(cQueue.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3));;
    }
}
