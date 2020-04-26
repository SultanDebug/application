package com.hzq.netty.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huangzq
 * @title: MergeKLists
 * @projectName applications
 * @date 2020/4/13 16:25
 */
public class MergeKLists {
    /**
     * 23. 合并K个排序链表
     * 合并 2【k】 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     */

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {

        //有空链表的情况
        if(l1==null && l2 ==null){
            return null;
        }else if(l1 == null){
            return l2;
        }else if(l2 == null){
            return l1;
        }

        //两链表深度都为1
        if(l1.next==null&&l2.next==null){
            if(l1.val>l2.val){
                l2.next = l1;
                return l2;
            }else{
                l1.next = l2;
                return l1;
            }
        }

        ListNode t1 = l1.val>l2.val ? l1 : (l1.next == null ? l1 : l1.next);
        ListNode t2 = l1.val>l2.val ? (l2.next == null ? l2 : l2.next) : l2;

        ListNode res = l1.val>l2.val ? l2 : l1;
        ListNode t3 = res;

        //其中一个链表深度为1且需要作为头结点的情况  会导致循环链表栈溢出超时
        if(t2 == t3){
            l2.next = l1;
            return l2;
        }else if(t1 == t3){
            l1.next = l2;
            return l1;
        }

        while (true){

            if(t1.val>t2.val){
                t3.next = t2;
                t3 = t3.next;
                if(t2.next!=null)
                    t2 = t2.next;
                else{
                    t3.next = t1;
                    break;
                }
            }else{
                t3.next = t1;
                t3 = t3.next;
                if(t1.next!=null)
                    t1 = t1.next;
                else {
                    t3.next = t2;
                    break;
                }
            }

        }


        return res;
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists==null){
            return null;
        }
        if(lists.length==0){
            return null;
        }
        if(lists.length==1){
            return lists[0];
        }

        ListNode listNode = lists[0];
        for (int i = 1; i < lists.length; i++) {
            listNode = this.mergeTwoLists(listNode,lists[i]);
        }
        return listNode;
    }


    /**
     * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null
     * 方法1：hash表记录映射
     * 方法2：原地复制节点  拆分链表
     * @param head
     * @return
     */

    public static Node copyRandomList(Node head) {
        Map<Node,Node> map = new HashMap<>();

        Node tmp = head;

        while (tmp != null){
            Node node = new Node(tmp.val);
            map.put(tmp,node);
            tmp=tmp.next;
        }

        Node headCopy = map.get(head);

        tmp = head;
        while (tmp != null){
            Node node = map.get(tmp);
            Node nodenext = map.get(tmp.next);
            Node noderandom = map.get(tmp.random);

            node.next = nodenext;
            node.random = noderandom;

            tmp=tmp.next;
        }

        return headCopy;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);

        ListNode a2 = new ListNode(5);
        a1.next = a2;

        ListNode a3 = new ListNode(7);
        a2.next = a3;

        ListNode b1 = new ListNode(2);

        ListNode b2 = new ListNode(6);
        b1.next = b2;

        ListNode b3 = new ListNode(8);
        b2.next = b3;

        ListNode c1 = new ListNode(3);

        ListNode c2 = new ListNode(5);
        c1.next = c2;

        ListNode c3 = new ListNode(9);
        c2.next = c3;

        ListNode[] listNodes = {a1,b1,c1};

        MergeKLists mergeKLists = new MergeKLists();
//        mergeKLists.mergeTwoLists(a1,b1);

        ListNode listNode = mergeKLists.mergeKLists(listNodes);

        System.out.println(listNode.toString());

        /*Node a1 = new Node(1);

        Node a2 = new Node(3);
        a1.next = a2;
        a1.random=null;

        Node a3 = new Node(6);
        a2.next = a3;
        a2.random = a1;

        a3.next = null;
        a3.random = a1;

        System.out.println(JSON.toJSONString(copyRandomList(null)));*/
    }

}
