
package com.hzq.algo.leetcode.course;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 功能说明
 *
 * @author 黄震强
 * @version 1.0.0
 * @date 2021/3/9 16:33
 */
public class MaxQueue {
    private Queue<Integer> a;
    private LinkedList<Integer> b;
    public MaxQueue() {
        a=new LinkedList<>();
        b=new LinkedList<>();
    }

    public int max_value() {
        return b.isEmpty()?-1:b.getFirst();
    }

    public void push_back(int value) {
        a.add(value);
        if(b.isEmpty()){
            b.addLast(value);
        }else{
            while(true){
                if(!b.isEmpty()&&b.getLast()<value){
                    b.removeLast();
                }else{
                    b.addLast(value);
                    break;
                }
            }
        }
    }

    public int pop_front() {
        Integer poll = a.poll();
        if(poll!=null&&poll.equals(b.peek())){
            b.removeFirst();
        }
        return poll==null?-1:poll;
    }

    public static int strToInt(String str) {
        char a = '-',b='+',c='0',d='9';
        int res = 0 , flag = 1,max = Integer.MAX_VALUE/10;
        boolean first = false;
        for (int i = 0; i < str.length(); i++) {
            char t = str.charAt(i);
            if(!first&&t==' '){}
            else if(!first){
                if(t==a || t==b || t>c && t<=d){
                    first=true;
                    if(t==a){flag=-1;}
                    else if(t==b){flag=1;}
                    else {res=res*10+(t-c);}
                }else if(t=='0'){
                    first=true;
                }
                else{
                    return 0;
                }
            }
            else{
                if(t>=c&&t<=d){
                    if(res>max||(res==max&&t>'7')){
                        return (flag==1?Integer.MAX_VALUE:Integer.MIN_VALUE);
                    }else res = res*10+(t-c);}
                else break;
            }
        }

        return res*flag;
    }

    /**
     * 224. 基本计算器  栈
     * 
     * @param
     * @return 
     * @author 黄震强
     * @version 1.0.0
     * @date 2021/3/10 18:40
    */
    public static int calculate(String s) {
        Stack<String> stack = new Stack<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i]==' '){
                continue;
            }else if(chars[i]=='('||chars[i]=='+'||chars[i]=='-'){
                if(chars[i]=='-'){
                    stack.push(new String("+"));
                }else{
                    stack.push(new String(Arrays.copyOfRange(chars,i,i+1)));
                }

            }else if(chars[i]>='0' && chars[i]<='9'){
                int tmp = 0;
                int flag = 1;
                int i1 = i;
                for (; i1 < chars.length; i1++) {
                    if(chars[i1]<'0' || chars[i1]>'9'){break;}
                    if(i1>0 && chars[i1-1]=='-'){flag=-1;}
                    tmp = tmp*10 + (chars[i1]-'0');
                }
                i=i1-1;
                stack.push(new String(tmp*flag+""));
            }else{
                while(true){
                    if(stack.size()<3){return Integer.parseInt(stack.pop());}
                    String b = stack.pop();
                    String f = stack.pop();
                    String a = stack.pop();
                    int cal = cal(f.charAt(0), Integer.parseInt(a), Integer.parseInt(b));
                    if(stack.isEmpty())break;
                    if(stack.peek().equals("(")){
                        stack.pop();
                        stack.push(cal+"");
                        break;
                    }else{
                        stack.push(cal+"");
                    }
                }
            }
        }
        if(stack.size()==1){
            return Integer.parseInt(stack.pop());
        }
        while(true){
            if(stack.size()<3){return Integer.parseInt(stack.pop());}
            String b = stack.pop();
            String f = stack.pop();
            String a = stack.pop();
            int cal = cal(f.charAt(0), Integer.parseInt(a), Integer.parseInt(b));
            if(stack.isEmpty()){
                stack.push(cal+"");
                break;
            }
            if(stack.peek().equals("(")){
                stack.pop();
                stack.push(cal+"");
                break;
            }else{
                stack.push(cal+"");
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public static int cal(char flag , int a , int b){
        switch (flag){
            case '-' : return a-b;
            case '+' : return a+b;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        /*MaxQueue maxQueue = new MaxQueue();
        maxQueue.push_back(5);
        maxQueue.push_back(2);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());
        maxQueue.push_back(6);
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());
        System.out.println(maxQueue.pop_front());
        System.out.println(maxQueue.max_value());*/
        System.out.println(calculate("- (3 + (4 + 5))"));
    }
}
