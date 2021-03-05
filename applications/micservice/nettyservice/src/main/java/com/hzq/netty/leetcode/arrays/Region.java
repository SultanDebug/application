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


    /**
     * 766. 托普利茨矩阵
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/2/22 11:36
    */
    public static boolean isToeplitzMatrix(int[][] matrix) {
        if(matrix.length<=1){return true;}
        int x = 1;
        for (;x<matrix.length;x++){
            if(matrix[x].length<=1){return true;}
            int y = 1;
            for (;y<matrix[x].length;y++){
                if(matrix[x][y]!=matrix[x-1][y-1]){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 7. 整数反转
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/2/23 10:47
    */
    public static int reverse(int x) {

        return 0;
    }

    /**
     * 1052. 爱生气的书店老板
     *  无扩展版本  一次遍历
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/2/23 10:48
    */
    public static int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int res = 0;
        int max = 0;
        int reduce = 0;

        if(X>=customers.length){
            for (int customer : customers) {
                res+=customer;
            }
            return res;
        }

        for (int i = 0; i < customers.length; i++) {
            int mt = 0;
            int rt = 0;
            int m = i+X-1;
            for(int k = i+X;k<customers.length;k++){
                if(grumpy[k]==1){
                    m = k-1;
                    break;
                }else{
                    m=k;
                }
            }
            for (int j=0;i+j<=m;j++){
                if(i+j<customers.length){
                    mt += customers[i+j];
                    if(grumpy[i+j]==1){
                        rt+=customers[i+j];
                    }
                }
            }
            if(mt>max){
                max = mt;
                reduce = rt;
            }
            if(grumpy[i]==0){
                res+=customers[i];
            }
        }

        return res+reduce;
    }

    /**
     * 1052. 爱生气的书店老板
     *  扩展版本
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/2/23 10:48
     */
    public static int maxSatisfied1(int[] customers, int[] grumpy, int X) {
        int res = 0;
        int max = 0;
        int mn=0,mm=0;
//        int reduce = 0;

        if(X>=customers.length){
            for (int customer : customers) {
                res+=customer;
            }
            return res;
        }
        int n=0;
        int m = 0;
        for (int i = 0; i < customers.length; i++) {
            int mt = 0;
            n=i;
            int nt = i;
            for(int k = i;k<customers.length;k++){
                nt=k;
                if(grumpy[k]==1){
                    break;
                }
            }
            m = Math.min(nt + X -1, customers.length - 1);
            if(m!=customers.length-1){
                for(int k = m+1;k<customers.length;k++){
                    if(grumpy[k]==1){
                        m = k-1;
                        break;
                    }else{
                        m=k;
                    }
                }
            }

            System.out.println(i+":"+n+"/"+m);
            for (int j=n;j<=m;j++){
                mt += customers[j];
            }
            if(mt>max){
                max = mt;
                mn=n;
                mm=m;
            }
        }

        for (int i = 0; i < mn; i++) {
            if(grumpy[i]==0){
                res += customers[i];
            }
        }
        res+=max;
        if(mm<customers.length-1){
            for (int i = mm+1; i < customers.length; i++) {
                if(grumpy[i]==0){
                    res += customers[i];
                }
            }
        }


        System.out.println(mn+"/"+mm);

        return res;
    }



    public static int maxSatisfied2(int[] customers, int[] grumpy, int X) {
        int res = 0;
        int reduce = 0;

        /*for (int i = 0;i<customers.length;i++) {
            if(grumpy[i]==0){
                res+=customers[i];
            }
        }*/

        for (int i = 0; i < customers.length; i++) {
            if(grumpy[i]==0){
                res+=customers[i];
            }
            int rt = 0;
            int m = i+X-1<customers.length?i+X-1:customers.length-1;
            for (int j=i;j<=m;j++){
                    if(grumpy[j]==1){
                        rt+=customers[j];
                    }
            }
            if(rt>reduce){
                reduce = rt;
            }
        }

        return res+reduce;
    }


    /**
     * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
     * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/3/1 16:51
    */
    public static int firstMissingPositive(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]>=0){
                map.put(nums[i],1);
            }
            if(max<nums[i]){
                max = nums[i];
            }
        }
        for (int i = 1; i <= max; i++) {
            if(map.get(i)==null){
                return i;
            }
            if(i==max){
                return i+1;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
//        characterReplacement("AABABBAAA",2);
        /*int[] a = new int[]{1,2,3,4};
        System.out.println(permute(a));*/

        /*int[] a = new int[]{1,2,1,3,4};
        System.out.println(subarraysWithKDistinct(a,3));*/

//        int m[][] = new int[][]{{1,2,3,4},{5,1,2,3},{9,5,1,2}};
        /*int m[][] = new int[][]{{1,2},{2,1}};
        System.out.println(isToeplitzMatrix(m));*/

        /*int x=2;
        for (int i = 1; i < 30; i++) {
            x*=2;
        }

        int y = 1073741824;
        int t = y;
        while(y>0){
            t=y;
            y++;
        }

        int z = -2147483647;
        System.out.println(Integer.MAX_VALUE+"/"+Integer.MIN_VALUE);
        String s = Integer.toBinaryString(2147483647);
        System.out.println(s+"/"+s.length());
        String s1 = Integer.toBinaryString(-2147483648);
        System.out.println(s1+"/"+s1.length());*/

//        int [] customers = {1,0,1,2,1,1,7,5}, grumpy = {0,1,0,1,0,1,0,1};
//        int [] customers = {4,10,10}, grumpy = {1,1,0};
//        int [] customers = {2,6,6,9}, grumpy = {0,0,1,1};
//        int [] customers = {7,8,8,6}, grumpy = {0,1,0,1};

//        System.out.println(maxSatisfied2(customers,grumpy,2));

        int [] customers = {};
        System.out.println(firstMissingPositive(customers));

    }
}
