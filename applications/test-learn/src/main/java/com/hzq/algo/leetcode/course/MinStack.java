
package com.hzq.algo.leetcode.course;

import java.math.BigDecimal;
import java.math.RoundingMode;
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


    private static BigDecimal getRate(Integer stock, Integer sale) {
        stock = stock == null ? 0 : stock;
        sale = sale == null ? 0 : sale;
        BigDecimal actStock = new BigDecimal(stock);
        BigDecimal sellStock = new BigDecimal(sale);

        if (stock == 0 || sale == 0) {
            return BigDecimal.ZERO;
        }

        if (stock.equals(sale)) {
            return new BigDecimal(100);
        }

        BigDecimal res = sellStock.divide(actStock, 4, RoundingMode.HALF_UP).multiply(new BigDecimal(100));
        if (res.equals(BigDecimal.ZERO)) {
            res = new BigDecimal(0.01);
        }

        return res.setScale(2, RoundingMode.HALF_UP);

    }

    public static String removeDuplicates(String S) {
        int t = 0;
        int[] f = new int[S.length()];
        for (int i = 0; i < S.length(); i++) {
            if(f[t]==1){
                t=i;
            }
            if(i!=t&&f[i]==0){
                if(S.charAt(i)==S.charAt(t)){
                    f[i]=1;
                    f[t]=1;
                    t=0;i=0;
                }
                t=i;
            }
        }
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < f.length; i++) {
            if(f[i]==0){
                s.append(S.charAt(i));
            }
        }
        return s.toString();
    }

    public static String removeDuplicates1(String S) {
            LinkedList<Character> stack = new LinkedList<>();
            for (int i = 0; i < S.length(); i++) {
                char t = S.charAt(i);
                Character peek = stack.peek();
                if(peek==null){
                    stack.push(t);
                    continue;
                }
                if(peek.equals(t)){
                    stack.pop();
                }else {
                    stack.push(t);
                }
            }
            StringBuilder s = new StringBuilder();
            while(true){
                if (stack.isEmpty()){
                    break;
                }
                s.append(stack.removeLast().charValue());
            }
            return s.toString();
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
//        MinStack cQueue = new MinStack();
//        System.out.println(cQueue.reverseLeftWords("abcde",1));;
//        System.out.println(cQueue.maxSlidingWindow(new int[]{1,3,-1,-3,5,3,6,7},3));;

        System.out.println(removeDuplicates1("aaaa"));
    }
}
