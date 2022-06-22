package com.hzq.netty.leetcode.arrays;

import com.google.common.collect.Lists;
import com.hzq.netty.leetcode.tree.Node;
import com.hzq.netty.leetcode.tree.TreeNode;

import java.util.*;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-04-18
 */
public class Dp {
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

    public static List<Set<String>> dfs(List<Set<String>> accounts) {
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

        if (!flag) {
            return result;
        } else {
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


    /**
     * 剑指 Offer 49. 丑数
     * 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     *
     * @Description: TODO
     * @Author: Huangzq
     * @Date: 2022/1/30 16:30
     **/
    public static int nthUglyNumber(int n) {
        int x = 0, y = 0, z = 0;
        int[] dp = new int[n];
        int xx = 0, yy = 0, zz = 0;
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            xx = dp[x] * 2;
            yy = dp[y] * 3;
            zz = dp[z] * 5;

            int t = Math.min(Math.min(xx, yy), zz);
            if (xx == t) {
                dp[i] = xx;
                x++;
            }
            if (yy == t) {
                dp[i] = yy;
                y++;
            }
            if (zz == t) {
                dp[i] = zz;
                z++;
            }
        }
        return dp[n - 1];
    }

    /**
     * 剑指 Offer 60. n 个骰子的点数
     * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
     * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率
     * @param:
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/2/21 19:50
     */
    public static double[] dicesProbability(int n) {
        double[][] tmp = new double[n+1][6*n+1];
        for (int i = 1; i < 7; i++) {
            tmp[1][i] = 1.0/6.0;
        }



        for (int i = 2; i <= n; i++) {
            for (int j = i; j <= 6*i; j++) {
                double t = 0;
                for (int k = j; k>=i && j-k<6 ; k--) {
                    t+=tmp[i-1][k-1]/6.0;
                }
                tmp[i][j]=t;
            }
        }
        double[] doubles = Arrays.copyOfRange(tmp[n], n, tmp[n].length);
        for (int j = 0; j < doubles.length; j++) {
            System.out.print(doubles[j]+"/");
        }

        /*double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        for (int i = 2; i <= n; i++) {
            double[] tmp = new double[5 * i + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    tmp[j + k] += dp[j] / 6.0;
                }
            }
            dp = tmp;
        }

        for (int j = 0; j < dp.length; j++) {
            System.out.print(dp[j]+"/");
        }*/

//        return Arrays.copyOfRange(tmp[n],n,tmp[n].length);
        return null;
    }

    /**
     * 剑指 Offer 63. 股票的最大利润
     * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
     * @param:
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/2/22 9:18
     */
    public static int maxProfit(int[] prices) {
        if(prices.length==0)return 0;
        int min=prices[0],max=0;
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min,prices[i]);
            max = Math.max(max,prices[i]-min);
        }

        return max;
    }

    /**
     * 剑指 Offer 12. 矩阵中的路径
     * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false
     * @param: 
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/2/22 16:17
     */
    public static boolean exist(char[][] board, String word) {
        if(board.length==0){return false;}
        char[] words = word.toCharArray();
        int[][] flags = new int[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if(board[i][j]==words[0]){
                    if(existDfs(i,j,0,board,flags,words)){
                        return true;
                    };
                }
            }
        }
        return false;
    }

    public static boolean existDfs(int i ,int j,int pos,char[][] board,int[][] flags,char[] words){
        flags[i][j]=1;
        if(pos==words.length-1){
            flags[i][j]=0;
            return true;
        }

        if(i>0&&board[i-1][j]==words[pos+1]&&flags[i-1][j]==0){
            //up
            if(existDfs(i-1,j,pos+1,board,flags,words)){
                flags[i][j]=0;
                return true;
            }
        }

        if(i<board.length-1&&board[i+1][j]==words[pos+1]&&flags[i+1][j]==0){
            //down
            if(existDfs(i+1,j,pos+1,board,flags,words)){
                flags[i][j]=0;
                return true;
            }
        }

        if(j>0&&board[i][j-1]==words[pos+1]&&flags[i][j-1]==0){
            //left
            if(existDfs(i,j-1,pos+1,board,flags,words)){
                flags[i][j]=0;
                return true;
            }
        }

        if(j<board[i].length-1&&board[i][j+1]==words[pos+1]&&flags[i][j+1]==0){
            //right
            if(existDfs(i,j+1,pos+1,board,flags,words)){
                flags[i][j]=0;
                return true;
            }
        }

        flags[i][j] = 0;
        return false;
    }


    /**
     * 剑指 Offer 13. 机器人的运动范围
     * 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
     * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
     * 也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，
     * 机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
     * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。
     * 请问该机器人能够到达多少个格子
     *
     * @param: 
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/2/22 16:18
     */
    public static int movingCount(int m, int n, int k) {
        int [][] cells = new int[m][n];
//        int i = movDfs(cells, 0, 0,k);
        int ii = movBfs(cells,k);
        return ii;
    }
    static int flag = 1;
    public static int movDfs(int[][] cells,int i,int j,int k){
        int res = 0;
        if(i>cells.length-1 || j > cells[i].length-1){
            return 0;
        }

        cells[i][j]=flag;
        res+=1;

        if(i<cells.length-1 && cells[i+1][j]==0&&check(i+1,j,k)){
            //down
            flag++;
            res+=movDfs(cells,i+1,j,k);
        }

        if(j<cells[i].length-1 && cells[i][j+1]==0&&check(i,j+1,k)){
            //right
            flag++;
            res+=movDfs(cells,i,j+1,k);
        }

        return res;
    }

    public static boolean check(int i,int j,int n){
        return i%10+(i/10)%10+(i/100)%10+j%10+(j/10)%10+(j/100)%10<=n;
    }

    public static int movBfs(int[][] cells,int k){
        int res = 0;
        Queue<int[]> queue = new ArrayDeque<>(cells.length);

        queue.add(new int[]{0,0});

        cells[0][0] = flag;
        flag++;
        while (!queue.isEmpty()){
            int [] poll = queue.poll();
            int i = poll[0];
            int j = poll[1];

            res ++;
            if(i<=cells.length-1){
                if(i<cells.length-1 && cells[i+1][j] == 0 && check(i+1,j,k)){
                    cells[i+1][j] = flag;
                    flag++;
                    queue.add(new int[]{i+1,j});
                }
                if(j<cells[i].length-1 && cells[i][j+1] == 0 && check(i,j+1,k)){
                    cells[i][j+1] = flag;
                    flag++;
                    queue.add(new int[]{i,j+1});
                }
            }
        }
        return res;
    }

    /**
     * 剑指 Offer 26. 树的子结构
     * 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
     * B是A的子结构， 即 A中有出现和B相同的结构和节点值
     * @param: 
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/2/23 10:58
     */
    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        if(A==null || B == null){return false;}
        return subDfs2(A,B);
    }

    //错误 只判断  未遍历
    public static boolean subDfs1(TreeNode a, TreeNode b){
        if(b==null){return true;}
        if(a==null){return false;}

        if(a.val==b.val){
            boolean left = subDfs1(a.left,b.left);
            boolean right = subDfs1(a.right,b.right);
            return left && right;
        }else{
            boolean left = subDfs1(a.left,b);
            boolean right = subDfs1(a.right,b);
            return left || right;
        }
    }

    //未匹配时保证遍历所有节点
    public static boolean subDfs2(TreeNode a, TreeNode b){
        return (a!=null&&b!=null)&&(subDfs3(a,b)||subDfs2(a.left,b)||subDfs2(a.right,b));
    }
    //判断遍历点是否找到子结构
    public static boolean subDfs3(TreeNode a, TreeNode b){
        if(b==null){return true;}
        if(a==null || a.val!=b.val){return false;}

        boolean left = subDfs3(a.left,b.left);
        boolean right = subDfs3(a.right,b.right);
        return left && right;
    }


    /**
     * 剑指 Offer 27. 二叉树的镜像
     * 请完成一个函数，输入一个二叉树，该函数输出它的镜像
     * @param:
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/2/23 14:09
     */
    public static TreeNode mirrorTree(TreeNode root) {
        if(root==null)return null;
        TreeNode tmp = null;
        tmp = root.left;
        root.left=root.right;
        root.right=tmp;

        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    /**
     * 剑指 Offer 28. 对称的二叉树
     * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的
     * @param:
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/2/23 14:15
     */
    public static boolean isSymmetric(TreeNode root) {
        if(root==null){return false;}
        return symDfs(root.left,root.right);
    }

    public static boolean symDfs(TreeNode a,TreeNode b){
        if(a==null&&b==null){
            return true;
        }else if(a!=null&&b!=null){
            boolean left = false;
            boolean right = false;
            if(a.val==b.val){
                left = symDfs(a.left,b.right);
                right = symDfs(a.right,b.left);
                return left && right;
            }else return false;
        }else return false;
    }

    /**
     * 剑指 Offer 32 - I. 从上到下打印二叉树
     * 从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印
     * @param:
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/2/23 14:42
     */
    public static int[] levelOrder(TreeNode root) {
        if(root==null){return new int[]{};}
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<Integer> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode tmp = queue.poll();
            list.add(tmp.val);

            if(tmp.left!=null){
                queue.add(tmp.left);
            }

            if(tmp.right!=null){
                queue.add(tmp.right);
            }
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }

    //错误
    public static int[] levelDfs(TreeNode root){
        if(root==null){return new int[]{};}
        List<Integer> list = new ArrayList<>();
        levelDfs(root,list);
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
    public static void levelDfs(TreeNode root ,List<Integer> list){
        if(root==null){return;}
        list.add(root.val);
        levelDfs(root.left,list);
        levelDfs(root.right,list);
    }

    /**
     * 剑指 Offer 32 - II. 从上到下打印二叉树 II
     * 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行
     * @param:
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/2/23 14:55
     */
    public static List<List<Integer>> levelOrderList(TreeNode root) {
        if(root==null){return new ArrayList<>();}
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> list = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            List<Integer> tmp = new ArrayList<>();

            for (int i = queue.size(); i > 0; i--) {
                TreeNode poll = queue.poll();
                tmp.add(poll.val);
                if(poll.left!=null){
                    queue.add(poll.left);
                }

                if(poll.right!=null){
                    queue.add(poll.right);
                }
            }
            list.add(tmp);
        }
        return list;
    }

    /**
     *
     * 剑指 Offer 32 - III. 从上到下打印二叉树 III
     * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推
     * @param:
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/2/23 15:01
     */
    public List<List<Integer>> levelOrderReverse(TreeNode root) {
        if(root==null){return new ArrayList<>();}
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> list = new ArrayList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()){
            LinkedList<Integer> tmp = new LinkedList<>();

            for (int i = queue.size(); i > 0; i--) {
                TreeNode poll = queue.poll();
                if(flag){
                    tmp.addLast(poll.val);
                }else tmp.addFirst(poll.val);
                if(poll.left!=null){
                    queue.add(poll.left);
                }

                if(poll.right!=null){
                    queue.add(poll.right);
                }
            }
            list.add(tmp);
            flag = !flag;
        }
        return list;
    }

    /**
     * 剑指 Offer 34. 二叉树中和为某一值的路径
     * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径
     * @param: 
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/2/23 15:22
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> tmp = new LinkedList<>();
        pathSumDfs(root,target,res,tmp,0);
        return res;
    }

    public static void pathSumDfs(TreeNode root,int tag,List<List<Integer>> res,LinkedList<Integer> tmp,int sum){
        if (root == null)return;
        tmp.add(root.val);
        sum+=root.val;

        if(root.left==null&&root.right==null){
            if(sum==tag){
                List<Integer> t = new ArrayList<>(tmp);
                res.add(t);
            }
            return;
        }

        if(root.left!=null){
            pathSumDfs(root.left,tag,res,tmp,sum);
            tmp.pollLast();
        }

        if(root.right!=null){
            pathSumDfs(root.right,tag,res,tmp,sum);
            tmp.pollLast();
        }
    }


    /**
     * 剑指 Offer 36. 二叉搜索树与双向链表
     * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向
     * @param: 
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/2/23 15:39
     */
    static Node head,tail;
    public static Node treeToDoublyList(Node root) {
        head = root;tail = root;
        linkDfs(root);
        if(head!=null){
            head.left=tail;
            tail.right=head;
        }
        return head;
    }

    public static void linkDfs(Node a){
        if(a==null){return;}
        Node left = a.left;
        Node right = a.right;
        a.left=null;a.right=null;
        linkSort(a);
        linkDfs(left);
        linkDfs(right);
    }

    public static void linkSort(Node a){
        if(a==head){return;}
        if(a.val<=head.val){
            head.left=a;
            a.right=head;
            head=a;
            return;
        }

        if(a.val>=tail.val){
            tail.right=a;
            a.left=tail;
            tail=a;
            return;
        }

        Node left = head;Node right = head.right;
        while (true){
            if(a.val<=right.val){
                left.right=a;
                a.left=left;
                a.right=right;
                right.left=a;
                break;
            }else{
                left = right;
                right=right.right;
            }
        }
    }

    static List<Integer> middles = new ArrayList<>();
    public static List<Integer> middle(Node root){
        if(root.left!=null){
            middle(root.left);
        }
        middles.add(root.val);
        if(root.right!=null){
            middle(root.right);
        }
        return middles;
    }

    /**
     * 剑指 Offer 37. 序列化二叉树 分别用来序列化和反序列化二叉树
     *
     * @param:
     * @return: {@link }
     * @author: Huangzq
     * @date: 2022/3/10 16:44
     */
    public static String serialize(TreeNode root) {
        if(root==null){
            return "[]";
        }
        List<Integer> res = new ArrayList<>();
        serDfs(root,res);
        return res.toString();
    }

    public static void serDfs(TreeNode root,List<Integer> res){
        res.add(root.val);

        if(root.right==null && root.left==null){return;}
        if(root.left!=null){
            serDfs(root.left,res);
        }else{
            res.add(null);
        }

        if(root.right!=null){
            serDfs(root.right,res);
        }else{
            res.add(null);
        }
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if(data==null || data.equals("[]")){
            return null;
        }
        data = data.substring(1,data.length()-1);
        String[] split = data.split(",");
        int root = Integer.parseInt(split[0]);
        TreeNode head = new TreeNode(root);
        TreeNode tail = head;

        for (int i = 1; i < split.length; i++) {
            int left = 2*i+1;
            int right = 2*i+2;
            if(left>= split.length){
                break;
            }

        }
        return null;
    }

    public static void main(String[] args) {

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left=t2;
        t1.right=t3;
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
//        t2.left=t4;
        t2.right=t5;

        TreeNode t6 = new TreeNode(6);
        TreeNode t7 = new TreeNode(7);
//        t3.left=t6;
        t3.right=t7;
        System.out.println((serialize(t1)));


        /*Node t1 = new Node(4);
        Node t2 = new Node(2);
        Node t3 = new Node(5);
        t1.left=t2;
        t1.right=t3;
        Node t4 = new Node(1);
        Node t5 = new Node(3);
        t3.left=t4;
        t3.right=t5;

//        Node node = treeToDoublyList(t1);
        System.out.println(middle(t1))*/

        /*TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(2);
        t1.left=t2;
        t1.right=t3;
        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(4);
        t2.left=t4;
        t2.right=t5;

        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(3);
//        t3.left=t6;
        t3.right=t7;

        boolean symmetric = isSymmetric(t1);
        System.out.println(symmetric);*/

        /*TreeNode t1 = new TreeNode(3);
        TreeNode t2 = new TreeNode(4);
        TreeNode t3 = new TreeNode(5);
        t1.left=t2;
        t1.right=t3;
        TreeNode t4 = new TreeNode(1);
        TreeNode t5 = new TreeNode(2);
        t2.left=t4;
        t2.right=t5;

        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(2);
        TreeNode t8 = new TreeNode(2);
        t6.left=t7;
        t6.right=t8;*/

        /*TreeNode t1 = new TreeNode(4);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        t1.left=t2;
        t1.right=t3;
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t2.left=t4;
        t2.right=t5;

        TreeNode t44 = new TreeNode(6);
        TreeNode t55 = new TreeNode(7);
        t3.left=t44;
        t3.right=t55;

        TreeNode t88 = new TreeNode(8);
        TreeNode t99 = new TreeNode(9);
        t4.left=t88;
        t4.right=t99;

        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(8);
        TreeNode t8 = new TreeNode(9);
        t6.left=t7;
        t6.right=t8;*/

        /*boolean subStructure = isSubStructure(t1, t6);
        System.out.println(subStructure);*/

//        System.out.println(movingCount(1,2,1));
//        System.out.println(check(12,10,1));
        /*char[][] board = new char[][]{
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        boolean abcced = exist(board, "ABCCED");*/

        /*char[][] board = new char[][]{
                {'C','A','A'},
                {'A','A','A'},
                {'B','C','D'}
        };
        boolean res = exist(board, "AAB");

        System.out.println(res);*/
        /*int[] a = new int[]{7,1,5,3,6,4};
        System.out.println(maxProfit(a));*/

//        dicesProbability(1);

        //测试仓库
//        System.out.println(nthUglyNumber(10));

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

        /*List<List<String>> accounts = new ArrayList<>();
        accounts.add(Arrays.asList("David", "David0@m.co", "David1@m.co"));
        accounts.add(Arrays.asList("David", "David3@m.co", "David4@m.co"));
        accounts.add(Arrays.asList("David", "David4@m.co", "David5@m.co"));
        accounts.add(Arrays.asList("David", "David2@m.co", "David3@m.co"));
        accounts.add(Arrays.asList("David", "David1@m.co", "David2@m.co"));

        System.out.println(JSON.toJSONString(accountsMerge1(accounts)));*/
    }

}
