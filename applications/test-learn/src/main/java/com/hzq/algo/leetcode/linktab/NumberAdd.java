
package com.hzq.algo.leetcode.linktab;

import java.util.Stack;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2020/12/11 11:25
 */
public class NumberAdd {
    /**
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：8 -> 0 -> 7
     * 原因：243 + 564 = 807
     * */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode result = null;
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        ListNode tmp1 = l1;
        ListNode tmp2 = l2;
        while (true){
            if(tmp1 != null){
                s1.push(tmp1.val);
                tmp1 = tmp1.next;
            }
            if(tmp2 != null){
                s2.push(tmp2.val);
                tmp2 = tmp2.next;
            }
            if(tmp1==null && tmp2==null){
                break;
            }
        }
        int in = 0;
        while (true){
            if(s1.isEmpty()&&s2.isEmpty()){
                if(in == 1){
                    ListNode listNode = new ListNode(1);
                    listNode.next = result;
                    result = listNode;
                }
                break;
            }

            Integer a = s1.isEmpty()?0:s1.pop();
            Integer b = s2.isEmpty()?0:s2.pop();

            int c = a+b;
            if(in == 1){
                c=a+b+in;
                in = 0;
            }

            int d = c;
            if(c>9){
                in = 1;
                d=c%10;
            }

            ListNode listNode = new ListNode(d);
            listNode.next = result;
            result = listNode;

        }

        return result;
    }

    /**
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        if(l1.next==null && l1.val==0
        && l2.next==null && l2.val==0){
            return new ListNode(0);
        }
        ListNode result = null;
        ListNode listNode = result , tmp = result;
        ListNode tmp1 = l1;
        ListNode tmp2 = l2;
        int in = 0;
        while (true){
            Integer a = tmp1 == null ? 0 : tmp1.val;
            Integer b = tmp2 == null ? 0 : tmp2.val;

            if(tmp1 == null&&tmp2 == null){
                if(in == 1){
                    tmp = new ListNode(1);
                    listNode.next = tmp;
                    listNode = tmp;
                }
                break;
            }

            if(tmp1 != null){
                tmp1 = tmp1.next;
            }

            if(tmp2 != null){
                tmp2 = tmp2.next;
            }

            int c = a+b;
            if(in == 1){
                c=a+b+in;
                in = 0;
            }

            int d = c;
            if(c>9){
                in = 1;
                d=c%10;
            }
            tmp = new ListNode(d);
            if(result == null){
                result = listNode = tmp;
            }else{
                listNode.next = tmp;
                listNode = tmp;
            }

        }

        return result;
    }

    public static void main(String[] args) {
        ListNode listNode1 = new ListNode(9);

        ListNode listNode2 = new ListNode(9);
        listNode1.next = listNode2;

        ListNode listNode3 = new ListNode(9);
        listNode2.next = listNode3;

        ListNode listNode4 = new ListNode(1);

        /*ListNode listNode5 = new ListNode(6);
        listNode4.next = listNode5;
        ListNode listNode6 = new ListNode(4);
        listNode5.next = listNode6;*/

        ListNode listNode = addTwoNumbers(listNode1, listNode4);

        while (true){
            System.out.println(listNode.val);
            if(listNode.next==null){break;}
            listNode = listNode.next;
        }
    }
}
