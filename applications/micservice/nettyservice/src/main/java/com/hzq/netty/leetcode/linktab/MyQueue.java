/*
 * 深圳市灵智数科有限公司版权所有.
 */
package com.hzq.netty.leetcode.linktab;

import java.util.LinkedList;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/3/5 11:41
 */
public class MyQueue {
    private LinkedList<Integer> in;
    private LinkedList<Integer> out;
    /** Initialize your data structure here. */
    public MyQueue() {
        in = new LinkedList<>();
        out = new LinkedList<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        in.addLast(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if(out.isEmpty()){
            change();
            if(out.isEmpty()){
                return -1;
            }
        }
        return out.removeFirst();
    }

    public void change(){
        while(!in.isEmpty()){
            Integer first = in.removeLast();
            out.addFirst(first);
        }
    }

    /** Get the front element. */
    public int peek() {
        if(out.isEmpty()){
            change();
            if(out.isEmpty()){
                return -1;
            }
        }
        return out.getFirst();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return in.isEmpty() && out.isEmpty();
    }

    public static void main(String[] args) {
        MyQueue cQueue = new MyQueue();
        cQueue.push(1);
        cQueue.push(2);
        System.out.println(cQueue.peek());
        System.out.println(cQueue.pop());
        System.out.println(cQueue.empty());
        System.out.println(cQueue.pop());
        System.out.println(cQueue.empty());
        System.out.println(cQueue.empty());
    }
}
