package com.hzq.netty.leetcode;

/**
 * @author Huangzq
 * @title: MergeKLists
 * @projectName applications
 * @date 2020/4/13 16:25
 */
public class MergeKLists {
    /**
     * 合并 2【k】 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
     */

    /*public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }*/

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

        return null;
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(1);

        ListNode a2 = new ListNode(3);
        a1.next = a2;

        ListNode a3 = new ListNode(6);
        a2.next = a3;

        ListNode b1 = new ListNode(2);

        /*ListNode b2 = new ListNode(3);
        b1.next = b2;

        ListNode b3 = new ListNode(4);
        b2.next = b3;*/

        MergeKLists mergeKLists = new MergeKLists();
        mergeKLists.mergeTwoLists(a1,b1);
    }

}
