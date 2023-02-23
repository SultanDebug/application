package com.hzq.algo.leetcode.linktab;


import com.xiaoleilu.hutool.json.JSON;
import com.xiaoleilu.hutool.json.JSONUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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

        ListNode listNode4 = new ListNode(9);
        listNode3.next = listNode4;

        ListNode listNode5 = new ListNode(9);
        listNode4.next = listNode5;

        RemoveNthFromEnd a = new RemoveNthFromEnd();
//        System.out.println(a.removeNthFromEnd(listNode1,5));
//        ListNode aaa = a.reverseBetween(listNode1,2,2);
//        ListNode aaa = a.addTwoNumbers(listNode1,listNode4);
        a.deleteNode(listNode2);
        System.out.println(JSONUtil.toJsonStr(listNode1));
    }

    /**
     * 链表标识数字，数字相加
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1.val == 0){
            return l2;
        }

        if(l2.val == 0){
            return l1;
        }

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        stackInit(s1,l1);
        stackInit(s2,l2);

        ListNode res = null;
        ListNode tmp = null;

        //进位考虑
        Integer jin = 0;

        while(true){
            if(s1.empty() && s2.empty()&& jin == 0){
                break;
            }

            Integer i1 =s1.empty() ? 0 : s1.pop();
            Integer i2 = s2.empty() ? 0 : s2.pop();

            Integer v = (i1+i2+jin)%10;
            jin = (i1+i2+jin)>=10 ? 1 : 0;

            ListNode listNode = new ListNode(v);

//            res = listNode;
            listNode.next = res;
            res = listNode;



        }

        return res;
    }

    /**
     * 递归压栈
     * @param stack
     * @param listNode
     */
    public void stackInit(Stack stack , ListNode listNode){
        stack.push(listNode.val);
        if(listNode.next == null){
            return;
        }
        stackInit(stack,listNode.next);
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


    /**
     * 实现一种算法，删除单向链表中间的某个节点（除了第一个和最后一个节点，不一定是中间节点），假定你只能访问该节点
     * 复制下一个节点
     * @param node
     */
    public void deleteNode(ListNode node) {
        ListNode tmp = node.next;

        node.val = tmp.val;
        node.next = tmp.next;
    }

}
