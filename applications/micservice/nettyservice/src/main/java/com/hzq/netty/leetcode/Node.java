package com.hzq.netty.leetcode;

import lombok.Data;

/**
 * @author Huangzq
 * @title: Node
 * @projectName applications
 * @date 2020/4/21 14:27
 */
public class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

}
