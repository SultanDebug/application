/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.netty.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/1/22 15:11
 */
public class Add {
    /**
     * 989. 数组形式的整数加法
     * 
     * @param
     * @return 
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/1/22 15:12
    */

    public static List<Integer> addToArrayForm2(int[] A, int K) {
        int t = 0;
        int i = A.length-1;
        List<Integer> res = new ArrayList<>();
        while(true){
            int k = K%10;
            if(i>=0&&K>0){
                int tmp = A[i]+k+t;
                if(tmp>9){
                    res.add(tmp%10);
                    t=1;
                }else{
                    res.add(tmp);
                    t=0;
                }
            }else if(i>=0&&K<=0){
                int tmp = A[i]+t;
                if(tmp>9){
                    res.add(tmp%10);
                    t=1;
                }else{
                    res.add(tmp);
                    t=0;
                }
            }else if(i<0&&K>0){
                int tmp = k+t;
                if(tmp>9){
                    res.add(tmp%10);
                    t=1;
                }else{
                    res.add(tmp);
                    t=0;
                }
            }else{
                if(t!=0){
                    res.add(1);
                }
                break;
            }
            i--;
            K /= 10;
        }
        Collections.reverse(res);
        return res;

    }

    public static List<Integer> addToArrayForm1(int[] A, int K) {
        int t = 0;
        int i = A.length-1;
        List<Integer> res = new ArrayList<>();
        while(true){
            int k = K%10;
            if(i>=0&&K>0){
                int tmp = A[i]+k+t;
                if(tmp>9){
                    res.add(tmp%10);
                    t=1;
                }else{
                    res.add(tmp);
                    t=0;
                }
            }else if(i>=0&&K<=0){
                int tmp = A[i]+t;
                if(tmp>9){
                    res.add(tmp%10);
                    t=1;
                }else{
                    res.add(tmp);
                    t=0;
                }
            }else if(i<0&&K>0){
                int tmp = k+t;
                if(tmp>9){
                    res.add(tmp%10);
                    t=1;
                }else{
                    res.add(tmp);
                    t=0;
                }
            }else{
                if(t!=0){
                    res.add(1);
                }
                break;
            }
            i--;
            K /= 10;
        }

        Integer[] result = new Integer[res.size()];
        int m = 0;
        int n = res.size()-1;
        while (true){
            if(m>n){break;}
            result[n] = res.get(m);
            result[m] = res.get(n);
            m++;n--;
        }

        List<Integer> list = Arrays.asList(result);

        return list;

    }

    public static List<Integer> addToArrayForm(int[] A, int K) {
        List<Integer> al = new ArrayList<>(A.length);
        for (int i = A.length - 1; i >= 0; i--) {
            al.add(A[i]);
        }
        List<Integer> kl = new ArrayList<>();
        while(true){
            int t = K%10;
            if(t!=0 || K/10>0){
                kl.add(t);
                K=K/10;
            }else{
                break;
            }
        }
        int size = Math.max(al.size(), kl.size());
        List<Integer> res = new ArrayList<>(size);
        int t = 0;
        for (int i = 0; i <= size; i++) {
            if(i<al.size()&&i<kl.size()){
                int tmp = al.get(i)+kl.get(i)+t;
                if(tmp>9){
                    res.add(tmp%10);
                    t=1;
                }else{
                    res.add(tmp);
                    t=0;
                }
            }else if(i<al.size()&&i>=kl.size()){
                int tmp = al.get(i)+t;
                if(tmp>9){
                    res.add(tmp%10);
                    t=1;
                }else{
                    res.add(tmp);
                    t=0;
                }
            }else if(i>=al.size()&&i<kl.size()){
                int tmp = kl.get(i)+t;
                if(tmp>9){
                    res.add(tmp%10);
                    t=1;
                }else{
                    res.add(tmp);
                    t=0;
                }
            }else{
                if(t!=0){
                    res.add(1);
                }
            }
        }
        Integer[] result = new Integer[res.size()];
        int i = 0;
        int j = res.size()-1;
        while (true){
            if(i>j){break;}
            result[j] = res.get(i);
            result[i] = res.get(j);
            i++;j--;
        }

        List<Integer> list = Arrays.asList(result);

        return list;

    }
    public static void main(String[] args) {
        int[] a = new int[]{2,1,5};
//        System.out.println(addToArrayForm1(a,806));
        System.out.println(Integer.MAX_VALUE);
        int x = 2;
        for (int i = 1; i < 31; i++) {
            x*=2;
        }
        System.out.println(x);
    }
}
