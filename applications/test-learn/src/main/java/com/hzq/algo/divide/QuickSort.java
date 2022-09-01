package com.hzq.algo.divide;

import com.xiaoleilu.hutool.json.JSONUtil;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/26 17:34
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5, 1, 9, 7, 3, 8, 2, 6, 4};
//        int[] arr = new int[]{5,1,9};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(JSONUtil.toJsonStr(arr));
    }

    public static void quickSort(int[] arr, int start, int end) {
        //特殊治理一个元素
        if (end <= start) {
            return;
        }

        //特殊治理两个元素
        if (end - start == 1) {
            if (arr[start] > arr[end]) {
                int tmp = arr[start];
                arr[start] = arr[end];
                arr[end] = tmp;
            }
            return;
        }

        int datum = arr[start];
        int i = start + 1, j = end;
        int mid = start;

        //寻找基准元素位置，顺便把小于基准放左边，大于基准放右边，注意点基准位置值是归并到左边还是右边，影响基准位置选取
        while (i < j) {
            while (arr[i] < datum && i < j) {
                i++;
            }

            while (arr[j] >= datum && i < j) {
                j--;
            }

            if (i < j) {
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            } else {
                mid = i - 1;
            }
        }

        //基准元素位置，如果基准位置值大于基准值，则基准位置取选取基准位减1，否则直接取当前找到的基准位
        if (arr[mid] >= arr[start]) {
            int tmp = arr[mid - 1];
            arr[mid - 1] = arr[start];
            arr[start] = tmp;
        } else {
            int tmp = arr[mid];
            arr[mid] = arr[start];
            arr[start] = tmp;
        }
        quickSort(arr, start, mid - 1);
        quickSort(arr, mid + 1, end);
    }
}
