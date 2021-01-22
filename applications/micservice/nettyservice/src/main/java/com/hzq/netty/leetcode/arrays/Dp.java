package com.hzq.netty.leetcode.arrays;

import com.alibaba.fastjson.JSON;

import java.util.*;

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

        /*String a = "";
        String b = "a";
        System.out.println(findTheDifference(a,b));*/

        /*int[] a = {1,2,3};
        int[] b = {1,2};
        System.out.println(findContentChildren(a,b));*/

        /*System.out.println(fib(6));*/
//        System.out.println(JSONObject.toJSONString(largeGroupPositions("aaa")));

        /*List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john00@mail.com"));
        accounts.add(Arrays.asList("John", "johnnybravo@mail.com"));
        accounts.add(Arrays.asList("John", "johnsmith@mail.com", "john_newyork@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));*/

        /*
        * [["John","johnsmith@mail.com"
        * ,"john_newyork@mail.com"],
        * ["John","johnsmith@mail.com",
        * "john00@mail.com"],
        * ["Mary","mary@mail.com"],
        * ["John","johnnybravo@mail.com"]]
        * */

        /*List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john_newyork@mail.com"));
        accounts.add(Arrays.asList("John","johnsmith@mail.com","john00@mail.com"));
        accounts.add(Arrays.asList("Mary", "mary@mail.com"));
        accounts.add(Arrays.asList("John","johnnybravo@mail.com"));*/

        List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("David", "David0@m.co", "David1@m.co"));
        accounts.add(Arrays.asList("David", "David3@m.co", "David4@m.co"));
        accounts.add(Arrays.asList("David", "David4@m.co", "David5@m.co"));
        accounts.add(Arrays.asList("David", "David2@m.co", "David3@m.co"));
        accounts.add(Arrays.asList("David", "David1@m.co", "David2@m.co"));

        System.out.println(JSON.toJSONString(accountsMerge1(accounts)));
    }

    public static List<List<String>> accountsMerge1(List<List<String>> accounts) {
        Map<String, List<Set<String>>> map = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            if (map.get(account.get(0)) == null) {
                Set<String> tmp = new HashSet<>();
                tmp.addAll(account.subList(1, account.size()));
                List<Set<String>> val = new ArrayList<>();
                val.add(tmp);
                map.put(account.get(0), val);
            } else {
                List<Set<String>> list = map.get(account.get(0));
                Set<String> tmp = new HashSet<>();
                tmp.addAll(account.subList(1, account.size()));
                list.add(tmp);
            }

        }

        List<List<String>> lists = new ArrayList<>();

        for (Map.Entry<String, List<Set<String>>> stringListEntry : map.entrySet()) {
            List<Set<String>> dfs = dfs(stringListEntry.getValue());
            for (Set<String> strings : dfs) {
                List<String> strings1 = new ArrayList<>();
                strings1.add(stringListEntry.getKey());
                strings1.addAll(sortString(strings));
                lists.add(strings1);
            }
        }
        return lists;
    }

    public static List<Set<String>> dfs (List<Set<String>> accounts){
        List<Set<String>> result = new ArrayList<>();
        boolean flag = false;
        for (int i = 0; i < accounts.size(); i++) {
            Set<String> account = accounts.get(i);
            if (result.size() == 0) {
                Set<String> tmp = new HashSet<>();
                tmp.addAll(account);
                result.add(tmp);
            } else {
                flag = false;
                for (String s : account) {
                    for (Set<String> strings : result) {
                        if (strings.contains(s)) {
                            strings.addAll(account);
                            flag = true;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }

                if (!flag) {
                    Set<String> tmp = new HashSet<>();
                    tmp.addAll(account);
                    result.add(tmp);
                }
            }

        }

        if(!flag){
            return result;
        }else{
            return dfs(result);
        }

    }

    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<Set<String>>> map = new HashMap<>();
        for (int i = 0; i < accounts.size(); i++) {
            List<String> account = accounts.get(i);
            if (map.get(account.get(0)) == null) {
                Set<String> tmp = new HashSet<>();
                tmp.addAll(account.subList(1, account.size()));
                List<Set<String>> val = new ArrayList<>();
                val.add(tmp);
                map.put(account.get(0), val);
            } else {
                List<Set<String>> list = map.get(account.get(0));
                boolean flag = false;
                for (String s : account.subList(1, account.size())) {
                    for (Set<String> strings : list) {
                        if (strings.contains(s)) {
                            strings.addAll(account.subList(1, account.size()));
                            flag = true;
                        }
                    }
                    if (flag) {
                        break;
                    }
                }

                if (!flag) {
                    Set<String> tmp = new HashSet<>();
                    tmp.addAll(account.subList(1, account.size()));
                    list.add(tmp);
                }
            }

        }

        List<List<String>> lists = new ArrayList<>();

        for (Map.Entry<String, List<Set<String>>> stringListEntry : map.entrySet()) {
            for (Set<String> strings : stringListEntry.getValue()) {
//                System.out.println(stringListEntry.getKey()+"-"+strings);
                List<String> strings1 = new ArrayList<>();
                strings1.add(stringListEntry.getKey());
                strings1.addAll(sortString(strings));
                lists.add(strings1);
            }
        }
        return lists;
    }

    public static List<String> sortString(Set<String> strings) {
        String[] arr = new String[strings.size()];
        String[] source = strings.toArray(arr);
        String str1 = "";
        String str2 = "";
        String temp = "";
        int length = 0;
        for (int i = 0; i < source.length; i++) {
            for (int m = 0; m < source.length - 1; m++) {
                str1 = source[m];
                str2 = source[m + 1];
                length = str1.length() > str2.length() ? str2.length() : str1.length();
                for (int j = 0; j < length; j++) {
                    if (str1.charAt(j) == str2.charAt(j)) {
                        continue;
                    } else if (str1.charAt(j) < str2.charAt(j)) {
                        break;
                    } else {
                        temp = str1;
                        source[m] = str2;
                        source[m + 1] = temp;
                    }
                }
            }

        }

        return Arrays.asList(source);
    }

    public static List<List<Integer>> largeGroupPositions(String s) {
        if (s.length() < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        char[] ss = s.toCharArray();
        int i = 0, j = 0;
        while (true) {
            i = j;
            while (true) {
                j++;
                if (j < ss.length && ss[i] == ss[j]) {
                    //nothing
                } else {
                    break;
                }
            }

            if (j - i >= 3) {
                List<Integer> list = new ArrayList<>(2);
                list.add(i);
                list.add(j - 1);
                res.add(list);
            }
            if (j > ss.length - 1) {
                break;
            }
        }

        return res;
    }

    /**
     * 509. 斐波那契数
     */
    public static int fib(int n) {
        /*if(n==0){return 0;}
        if(n==1){return 1;}
        int s = 0;
        int t1 = 0;
        int t2 = 1;
        for(int i = 2;i<=n;i++){
            s=t1+t2;
            t1=t2;
            t2=s;
        }
        return s;*/

        return fibDp(n, 1, 0);

    }

    public static int fibDp(int n, int t, int s) {
        if (n == 1) {
            return 1;
        }
        return s + fibDp(n - 1, s, t + s);
    }

    /**
     * 188. 买卖股票的最佳时机 IV
     */
    public static int maxProfit(int k, int[] prices) {
        return 0;
    }
    /**
     * 121. 买卖股票的最佳时机
     * */
    /*public static int maxProfit(int[] prices) {
        int i = 0 , j = prices.length-1;
        int r = 0;
        while(true){
            if(i>=j){break;}
            if(){}
        }

        return r;
    }*/

    /**
     * 455. 分发饼干
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int i = 0;
        int j = 0;
        int r = 0;

        for (; i < g.length; i++) {
            if (j == s.length) {
                break;
            }
            for (; j < s.length; ) {
                if (g[i] <= s[j]) {
                    r++;
                    j++;
                    break;
                }
                j++;
            }
        }
        return r;
    }


    /**
     * 389. 找不同
     */
    public static char findTheDifference(String s, String t) {
        char[] a = (s + t).toCharArray();
        int res = a[0];
        for (int i = 1; i < a.length; i++) {
            res = res ^ a[i];
        }
        return (char) res;
    }

    /**
     * 31. 栈的压入、弹出序列
     */
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
        /*if(pushed.length==0&&popped.length==0){
            return true;
        }else if(pushed.length==0 || popped.length==0){
            return false;
        }*/
        Stack<Integer> sin = new Stack<>();
        int k = 0, i = 0;

        for (; i < pushed.length; i++) {
            sin.push(pushed[i]);
            while (!sin.isEmpty() && sin.peek() == popped[k]) {
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
