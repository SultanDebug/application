
package com.hzq.algo.leetcode.arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/3/2 10:47
 */
public class NumMatrix {
    int[][] matrixT ;
    public NumMatrix(int[][] matrix) {
        if(matrix.length>0){
            matrixT = new int[matrix.length][matrix[0].length+1];
            for (int i = 0; i < matrix.length; i++) {
                for (int i1 = 0; i1 < matrix[i].length; i1++) {
                    matrixT[i][i1+1] = matrixT[i][i1]+matrix[i][i1];
                }
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int res = 0;
        if(matrixT==null||matrixT.length==0){return 0;}
        for (int i = row1; i <= row2; i++) {
            res += (matrixT[i][col2+1]-matrixT[i][col1]);
        }
        return res;
    }

    /**
     * 二进制1位计算
     *
     * @param
     * @return
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/3/3 14:56
    */
    public static int[] countBits(int num) {
        int[] res = new int[num+1];
        for (int i = 0; i <= num; i++) {
            int t = 0;
            char[] chars = Integer.toBinaryString(i).toCharArray();
            for (char aChar : chars) {
                if(aChar=='1'){
                    t++;
                }
            }
            res[i]=t;
        }
        return res;
    }

    /**
     * @Description: 俄罗斯套娃  深度搜索
     * @Author: Huangzq
     * @Date: 2021/3/6 9:29
     * @Param:
     * @Return:
     **/
    public static int maxEnvelopes(int[][] envelopes) {
        if(envelopes.length==0 || (envelopes[0].length==0)){
            return 0;
        }
        if(envelopes[0].length==1){return 1;}
        int [] status = new int[envelopes.length];
        int max = 0;
        for (int i = 0; i < status.length; i++) {
            int tmp = 0;
            status[i]=1;
            max = dfs(max,tmp,status,envelopes[i],envelopes);
            status[i]=0;
        }
        return max+1;
    }


    public static int dfs(Integer max ,Integer tmp,int[]status,int[] cur ,int[][] envelopes){
        for (int i = 0; i < status.length; i++) {
            int[] opt = envelopes[i];
            if(status[i]==0&& cur[0] < opt[0] && cur[1] < opt[1]){
                tmp++;
                status[i]=1;
                max = dfs(max,tmp,status,opt,envelopes);
                status[i]=0;
                if(tmp>max){max = tmp;}
                tmp--;
            }
        }
        return max;
    }

    /**
     * @Description: 俄罗斯套娃 严格递增
     * @Author: Huangzq
     * @Date: 2021/3/6 9:28
     * @Param:
     * @Return:
     **/
    public static int maxEnvelopes1(int[][] es) {
        int n = es.length;
        int[] f = new int[n];
        Arrays.sort(es, (e1, e2) -> {
            if(e1[0] == e2[0]) return e1[1] - e2[1];
            return e1[0] - e2[0];
        });
        int res = 0;
        for(int i = 0; i < n; i++){
            f[i] = 1;
            for(int j = 0; j < i; j++){
                if(es[j][1] < es[i][1]){
                    f[i] = Math.max(f[i], f[j] + 1);
                }
            }
            res = Math.max(res, f[i]);
        }
        return res;
    }

    /**
     * @Description: 503. 下一个更大元素 II
     * @Author: Huangzq
     * @Date: 2021/3/6 9:41
     * @Param:
     * @Return:
     **/
    public static int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int t = i+1==nums.length?0:i+1;
            while(t!=i){
                if(nums[t]>nums[i]){
                    res[i] = nums[t];
                    break;
                }
                t++;
                if(t==nums.length){t=0;}
            }
            if(t==i){
                res[i] = -1;
            }
        }
        return res;
    }


    static boolean[][] f;
    static List<List<String>> ret = new ArrayList<List<String>>();
    static List<String> ans = new ArrayList<String>();
    static int n;

    public static List<List<String>> partition(String s) {
        n = s.length();
        f = new boolean[n][n];
        for (int i = 0; i < n; ++i) {
            Arrays.fill(f[i], true);
        }

        for (int i = n - 1; i >= 0; --i) {
            for (int j = i + 1; j < n; ++j) {
                f[i][j] = (s.charAt(i) == s.charAt(j)) && f[i + 1][j - 1];
            }
        }

        dfs(s, 0);
        return ret;
    }

    public static void dfs(String s, int i) {
        if (i == n) {
            ret.add(new ArrayList<String>(ans));
            return;
        }
        for (int j = i; j < n; ++j) {
            if (f[i][j]) {
                ans.add(s.substring(i, j + 1));
                dfs(s, j + 1);
                ans.remove(ans.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        /*int[][] a = new int[][]{
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };*/

        /*int[][] a = new int[][]{

        };

        NumMatrix numMatrix = new NumMatrix(a);
        System.out.println(numMatrix.sumRegion(0,0,0,0));*/

        /*String s = "We are happy.";
        StringBuilder stringBuilder = new StringBuilder();
        char[] sa = s.toCharArray();
        for (int i = 0; i < sa.length; i++) {
            if(' ' == sa[i]){
                stringBuilder.append("%20");
            }else{
                stringBuilder.append(sa[i]);
            }
        }

        System.out.println(stringBuilder.toString());*/

        /*System.out.println(countBits(5));*/

//        int[][] envelopes = new int[][]{{5,4},{6,4},{6,7},{2,3}};
        /*int[][] envelopes = new int[][]{{4,5},{4,6},{6,7},{2,3},{1,1}};

        System.out.println(maxEnvelopes1(envelopes));*/

        /*int[] envelopes = new int[]{1,2,1};
        nextGreaterElements(envelopes);*/

        System.out.println(partition("ababa"));

    }
}
