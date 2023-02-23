
package com.hzq.algo.leetcode.course;

import java.util.LinkedList;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/3/3 14:57
 */
public class CQueue {
    private LinkedList<Integer> in;
    private LinkedList<Integer> out;
    public CQueue() {
        in = new LinkedList<>();
        out = new LinkedList<>();
    }

    public void appendTail(int value) {
        in.addLast(value);
    }

    public int deleteHead() {
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

    public static void main(String[] args) {
        CQueue cQueue = new CQueue();
        cQueue.appendTail(1);
        cQueue.appendTail(2);
        System.out.println(cQueue.deleteHead());
        cQueue.appendTail(3);
        cQueue.appendTail(4);
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
        System.out.println(cQueue.deleteHead());
    }
}
