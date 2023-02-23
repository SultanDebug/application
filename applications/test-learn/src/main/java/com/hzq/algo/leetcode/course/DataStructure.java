
package com.hzq.algo.leetcode.course;

import com.hzq.algo.leetcode.linktab.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/3/2 18:30
 */
public class DataStructure {
    /**
     * 剑指 Offer 06. 从尾到头打印链表
    * */
    public static int[] reversePrint(ListNode head) {
        if(head == null){
            return new int[]{};
        }
        LinkedList<Integer> linkedList = new LinkedList<>();
        while (true){
            linkedList.addFirst(head.val);
            head=head.next;
            if(head==null){
                break;
            }
        }
        int[] r = new int[linkedList.size()];
        int i=0;
        while(!linkedList.isEmpty()){
            r[i]=linkedList.removeFirst();
            i++;
        }
        return r;
    }

    /**
     * 剑指 Offer 06. 从尾到头打印链表 递归
     * */
    public static int[] reversePrint1(ListNode head) {
        List<Integer> list = new ArrayList<>();

        list = reversePrint1(list,head);

        int[] r = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            r[i]=list.get(i);
        }
        return r;
    }

    public static List reversePrint1(List<Integer> list ,ListNode head) {
        if(head==null){
            return list;
        }
        list = reversePrint1(list,head.next);
        list.add(head.val);

        return list;
    }

    public static ListNode reverseList(ListNode head) {
        ListNode res = null;
        while(head!=null){
            ListNode tmp = new ListNode(head.val);
            if(res == null){
                res = tmp;
            }else{
                tmp.next = res;
                res = tmp;
            }
            head = head.next;
        }
        return res;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l3 = new ListNode(3);
        ListNode l2 = new ListNode(2);

        l1.next = l3;
        l3.next = l2;

        System.out.println(reverseList(l1));;
    }
}
