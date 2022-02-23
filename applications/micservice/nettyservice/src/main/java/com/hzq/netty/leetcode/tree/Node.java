package com.hzq.netty.leetcode.tree;

/**
 * @Author: Huangzq
 * @Date: 2022/2/23 15:37
 */
public class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
}
