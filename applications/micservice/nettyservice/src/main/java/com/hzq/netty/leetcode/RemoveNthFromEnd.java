package com.hzq.netty.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-04-12
 */
public class RemoveNthFromEnd {
    public static void main(String[] args) {

        ListNode listNode1 = new ListNode(1);

        ListNode listNode2 = new ListNode(2);
        listNode1.next = listNode2;

        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;

        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode4;

        ListNode listNode5 = new ListNode(5);
        listNode4.next = listNode5;

        RemoveNthFromEnd a = new RemoveNthFromEnd();
//        System.out.println(a.removeNthFromEnd(listNode1,5));
        ListNode aaa = a.reverseBetween(listNode1,2,2);
        System.out.println(aaa);
    }

    /**
     * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点
     * 递归计数
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        head = remove(head,n)==n ? head.next:head;
        return head;
    }

    public int remove(ListNode listNode , int cur){
        if(listNode.next == null){
            return 1;
        }

        int a = remove(listNode.next,cur);

        if(a == cur){
            listNode.next = listNode.next.next;
        }

        return a+1;
    }


    /**
     * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转
     * list存储反转序列，记录头尾，一次扫描
     * @param head
     * @param m
     * @param n
     * @return
     */
    public ListNode reverseBetween(ListNode head, int m, int n) {

        List<ListNode> listNodes = new ArrayList<>();

        if(m==n&&m==1){return head;}

        ListNode tmp = head;

        ListNode start = null;
        ListNode end = null;

        int i = 1;
        while (true){
            if(i>n || tmp == null){
                break;
            }
            if(m!=1&&i==m-1){
                start = tmp;
            }
            if(i==n){
                end = tmp.next;
            }
            if(i>=m && i<=n){
                listNodes.add(tmp);
            }
            tmp = tmp.next;
            i++;
        }

        for (int j = listNodes.size()-1 ; j>=0;j--){
            if(start == null){
                start = listNodes.get(j);
                head = start;
            }else{
                start.next = listNodes.get(j);
                start = start.next;
                if(j==0){
                    start.next = end;
                }
            }

        }

        return head;
    }

}
