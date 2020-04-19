package com.hzq.netty.leetcode;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-04-18
 */
public class Dp {
    public static void main(String[] args) {
        int a = 3;

        Dp dp = new Dp();
        System.out.println(dp.divisorGame(a));
    }

    /**
     * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
     * @param num
     * @return
     */
    public int[] countBits(int num) {
        int a = num&(num-1);
        System.out.println(a);
        return null;
    }

    public boolean divisorGame(int N) {

        boolean flag = false;

        if(N == 1){
            return true;
        }

        for (int i = N-1; i > 0; i--) {
            if(i == 1 && N-i==1){flag = !flag;break;}

            if(N%i==0){
                N=N-i;
                flag = !flag;
                i=N-1;
            }

        }

        return flag;
    }
}
