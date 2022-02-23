package com.hzq.netty.leetcode.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Huangzq
 * @title: TreeNode
 * @projectName 二叉树
 * @date 2020/4/14 17:30
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int x) { val = x; }

    public static void main(String[] args) throws InterruptedException {
        List<TreeNode> list = new ArrayList<>();

        for (int i = 0; true; i++) {
            TreeNode treeNode = new TreeNode(i);
            list.add(treeNode);
            Thread.sleep(1000);
            System.out.println(i);
        }
    }
}
