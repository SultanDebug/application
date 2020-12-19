package com.hzq.netty.leetcode.arrays;

import java.util.Stack;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-04-18
 */
public class Dp {
    public static void main(String[] args) {
        /*int a = 3;

        int[] b = {7, 5, 1, 6, 4};

        Dp dp = new Dp();
        int[] res = dp.mergeSort(b);
        System.out.println(res);*/
//        System.out.println(dp.hammingWeight(5));
//        System.out.println(dp.waysToChange(900750));

        /*int[] b = {1,0};
        int[] a = {1,0};
        System.out.println(validateStackSequences(a,b));*/

        String a = "";
        String b = "a";
        System.out.println(findTheDifference(a,b));
    }


    /**
     * 389. 找不同
     * */
    public static char findTheDifference(String s, String t) {
        char[] a = (s+t).toCharArray();
        int res = a[0];
        for (int i = 1; i < a.length; i++) {
            res = res^a[i];
        }
        return (char) res;
    }

    /**
     *  31. 栈的压入、弹出序列
     * */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        /*if(pushed.length==0&&popped.length==0){
            return true;
        }else if(pushed.length==0 || popped.length==0){
            return false;
        }*/
        Stack<Integer> sin = new Stack<>();
        int k = 0,i = 0;

        for (;i < pushed.length; i++) {
            sin.push(pushed[i]);
            while (!sin.isEmpty() && sin.peek()==popped[k]){
                sin.pop();
                k++;
            }
        }
        return sin.isEmpty();
    }

    /**
     * mst51. 数组中的逆序对
     * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数
     *
     * @param nums
     * @return
     */
    public int reversePairs(int[] nums) {


        return 0;
    }

    /**
     * 归并计数逆序对
     *
     * @param nums
     * @return
     */
    public int mergeCount(int[] nums) {

        int avg = nums.length / 2;

        int left, right, res;

        if (nums.length <= 1) {
            return 0;
        } else {
            int[] tmp1 = new int[avg];
            System.arraycopy(nums, 0, tmp1, 0, avg);

            int[] tmp2 = new int[nums.length - avg];
            System.arraycopy(nums, avg, tmp2, 0, nums.length - avg);

            left = mergeCount(tmp1);
            right = mergeCount(tmp2);
        }

//        int[] res = new int[nums.length];

        int k = 0;
        int i = 0, j = 0;

        boolean lf = false, rf = false;

        /*while (k < nums.length
                && i < left.length
                && j < right.length) {
            if (left[i] > right[j]) {

                if (rf) {
                    res[k] = left[i];
                    i++;
                } else {
                    res[k] = right[j];
                    if (j == right.length - 1) {
                        rf = true;
                    } else {
                        j++;
                    }
                }


            } else {
                if (lf) {
                    res[k] = right[j];
                    j++;
                } else {
                    res[k] = left[i];
                    if (i == left.length - 1) {
                        lf = true;
                    } else {
                        i++;
                    }
                }
            }
            k++;
        }*/

        return 0;
    }

    /**
     * 归并排序
     *
     * @param nums
     * @return
     */
    public int[] mergeSort(int[] nums) {

        int avg = nums.length / 2;

        int[] left, right;

        if (nums.length <= 1) {
            return nums;
        } else {
            int[] tmp1 = new int[avg];
            System.arraycopy(nums, 0, tmp1, 0, avg);

            int[] tmp2 = new int[nums.length - avg];
            System.arraycopy(nums, avg, tmp2, 0, nums.length - avg);

            left = mergeSort(tmp1);
            right = mergeSort(tmp2);
        }

        int[] res = new int[nums.length];

        int k = 0;
        int i = 0, j = 0;

        boolean lf = false, rf = false;

        while (k < nums.length
                && i < left.length
                && j < right.length) {
            if (left[i] > right[j]) {

                if (rf) {
                    res[k] = left[i];
                    i++;
                } else {
                    res[k] = right[j];
                    if (j == right.length - 1) {
                        rf = true;
                    } else {
                        j++;
                    }
                }


            } else {
                if (lf) {
                    res[k] = right[j];
                    j++;
                } else {
                    res[k] = left[i];
                    if (i == left.length - 1) {
                        lf = true;
                    } else {
                        i++;
                    }
                }
            }
            k++;
        }

        return res;
    }


    /**
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     * 动态规划
     *
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int[] res = new int[num + 1];

        int b = 1, i = 0;

        res[0] = 0;

        while (b <= num) {

            while (i < b && i + b <= num) {
                res[i + b] = res[i] + 1;
                i++;
            }

            i = 0;

            b = b << 1;
        }

        return res;
    }

    /**
     * 191. 位1的个数
     * 编写一个函数，输入是一个无符号整数，返回其二进制表达式中数字位数为 ‘1’ 的个数（也被称为汉明重量）
     *
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int[] res = new int[n + 1];

        int b = 1, i = 0;

        res[0] = 0;

        while (b <= n) {

            while (i < b && i + b <= n) {
                res[i + b] = res[i] + 1;
                i++;
            }

            i = 0;

            b = b << 1;
        }

        return res[n];
    }

    /**
     * 1025. 除数博弈
     * <p>
     * 爱丽丝和鲍勃一起玩游戏，他们轮流行动。爱丽丝先手开局。
     * <p>
     * 最初，黑板上有一个数字 N 。在每个玩家的回合，玩家需要执行以下操作：
     * <p>
     * 选出任一 x，满足 0 < x < N 且 N % x == 0 。
     * 用 N - x 替换黑板上的数字 N 。
     * 如果玩家无法执行这些操作，就会输掉游戏。
     * <p>
     * 只有在爱丽丝在游戏中取得胜利时才返回 True，否则返回 false。假设两个玩家都以最佳状态参与游戏
     *
     * @param N
     * @return
     */
    public boolean divisorGame(int N) {

        boolean flag = false;

        if (N == 1) {
            return true;
        }

        for (int i = N - 1; i > 0; i--) {
            if (i == 1 && N - i == 1) {
                flag = !flag;
                break;
            }

            if (N % i == 0) {
                N = N - i;
                flag = !flag;
                i = N - 1;
            }

        }

        return flag;
    }

    /**
     * 08.11. 硬币
     * 硬币。给定数量不限的硬币，币值为25分、10分、5分和1分，编写代码计算n分有几种表示法。(结果可能会很大，你需要将结果模上1000000007)
     * 1.DP
     * 2.等差数列算法
     *
     * @param n
     * @return
     */
    public int waysToChange(int n) {

        /*int[] coins = {25,10,5,1};

        for (int i = 0; i < coins.length; i++) {

        }*/

        int res = 0;

        for (int i = 0; i * 25 <= n; i++) {
            int c = n - i * 25;
            int a = c / 10;
            int b = c % 10 / 5;

            res = (int) (res + ((long) (a + 1) * (a + b + 1)) % 1000000007) % 1000000007;

            System.out.println(i + "/" + res);
        }

        return res;
    }


}
