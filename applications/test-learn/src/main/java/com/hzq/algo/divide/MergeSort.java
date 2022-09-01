package com.hzq.algo.divide;

import com.xiaoleilu.hutool.json.JSONUtil;

import java.util.Arrays;

/**
 * 归并排序
 * 每次直接中分，直到不能分割，再归并排序
 * @author Huangzq
 * @description
 * @date 2022/8/26 16:48
 */
public class MergeSort {
    public static void main(String[] args) {
        //
        int[] arr = new int[]{5,1,9,7,3,8,2,6,4};
        int[] sort = sort(arr);
        System.out.println(JSONUtil.toJsonStr(sort));
    }

    /**
     * Description: 
     *  分治
     * @param 
     * @return 
     * @author Huangzq
     * @date 2022/8/26 17:16
     */
    public static int[] sort(int[] arr){
        if(arr.length <= 1){
            return arr;
        }

        int mid = arr.length/2;

        int[] left = sort(Arrays.copyOfRange(arr, 0, mid));
        int[] right = sort(Arrays.copyOfRange(arr, mid, arr.length));
        return merge(left,right);
    }

    /**
     * Description: 
     *  合并
     * @param 
     * @return 
     * @author Huangzq
     * @date 2022/8/26 17:15
     */
    public static int[] merge(int[] left,int[] right){
        int[] merge = new int[left.length+right.length];
        int i = 0;
        int j=0;
        int pos=0;
        while(pos<left.length+right.length){
            if(i==left.length){
                merge[pos]=right[j];
                j++;
            }else if(j==right.length){
                merge[pos]=left[i];
                i++;
            }else{
                if(left[i]<=right[j]){
                    merge[pos] = left[i];
                    i++;
                }else{
                    merge[pos] = right[j];
                    j++;
                }
            }
            pos++;
        }
        return merge;
    }
}
