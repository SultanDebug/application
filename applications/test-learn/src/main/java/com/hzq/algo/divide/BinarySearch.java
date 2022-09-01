package com.hzq.algo.divide;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/25 17:57
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4,5,6,7,9};
        /*int search = search(0, 8, arr);
        System.out.println(search);*/

        int search1 = searchByDoublePointer(8, arr);
        System.out.println(search1);
    }

    /**
     * Description:
     *  二分查找，栈实现
     * @param
     * @return
     * @author Huangzq
     * @date 2022/8/26 15:35
     */

    public static int searchByDoublePointer(int val ,int[] arr){
        int left = 0;
        int right = arr.length-1;
        while(left<right){
            int mid = (left+right)/2;
            if(val<arr[mid]){
                right = mid;
            }else if(val==arr[mid]){
                return mid;
            }else{
                left = mid+1;
            }
        }

        return -1;
    }

    /**
     * Description:
     *  二分查找，递归实现
     * @param
     * @return
     * @author Huangzq
     * @date 2022/8/26 15:34
     */
    public static int search(int pos , int val ,int[] arr){
        if(arr.length==1){
            if(val!=arr[0]){
                return -1;
            }else{
                return 0;
            }
        }
        int tmp = arr.length/2;
        if(val<arr[tmp]){
            int search = search(0, val, Arrays.copyOfRange(arr, 0, tmp));
            if(search==-1){return -1;}
            return pos+search;
        }else if(val==arr[tmp]){
            return pos+tmp;
        }else{
            int search = search(tmp, val, Arrays.copyOfRange(arr, tmp, arr.length));
            if(search==-1){return -1;}
            return pos+search;
        }
    }
}
