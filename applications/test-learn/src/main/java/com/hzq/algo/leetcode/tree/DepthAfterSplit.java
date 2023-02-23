package com.hzq.algo.leetcode.tree;

/**
 * 树深排序、遍历
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/7/24 16:59
 */
public class DepthAfterSplit {
    public int[] maxDepthAfterSplit(String seq) {
        return null;
    }

    public int kthSmallest(TreeNode root, int k) {
        if (root==null)return 0;
        int res = root.val;
        for (int i = 0; i < k; i++) {
            res = get(root,res);
        }
        return res;
    }

    private int get(TreeNode root,int mid){
        int min = mid;
        if(root.val<mid){
            min = root.val;
        }
        int left = mid;
        if(root.left!=null){
            left = get(root.left, min);
        }
        int right = mid;
        if(root.right!=null){
            right = get(root.right,min);
        }
        int t = mid;
        if(left<right){
            t = left;
        }else{
            t= right;
        }

        if(t<min){
            min = t;
        }

        return min;
    }

    public static void main(String[] args) {
        DepthAfterSplit t = new DepthAfterSplit();
//        t.maxDepthAfterSplit("(()())");

        TreeNode t1 = new TreeNode(8);
        TreeNode t2 = new TreeNode(5);
        TreeNode t3 = new TreeNode(10);

        t1.left = t2;
        t1.right = t3;

        TreeNode t4 = new TreeNode(3);
        TreeNode t5 = new TreeNode(7);

        t3.left = t4;
        t3.right = t5;

        TreeNode t6 = new TreeNode(4);
        TreeNode t7 = new TreeNode(9);

        t5.left = t6;
        t5.right = t7;

        int i = t.kthSmallest(t1, 3);
        System.out.println(i);

    }
}
