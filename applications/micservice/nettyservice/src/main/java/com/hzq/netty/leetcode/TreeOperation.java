package com.hzq.netty.leetcode;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Huangzq
 * @title: TreeOperation
 * @projectName applications
 * @date 2020/4/14 17:32
 */
public class TreeOperation {
    public static void main(String[] args) {
        TreeNode t1 = new TreeNode(1);

        TreeNode t2 = new TreeNode(2);
        t1.left = t2;

        TreeNode t3 = new TreeNode(3);
        t1.right = t3;

        TreeNode t4 = new TreeNode(4);
        t2.left = t4;

        TreeNode t5 = new TreeNode(5);
        t2.right = t5;

        TreeNode t6 = new TreeNode(6);

        TreeNode t7 = new TreeNode(7);
        t6.left = t7;

        TreeNode t8 = new TreeNode(8);
        t6.right = t8;

        TreeNode t9 = new TreeNode(9);
        t7.left = t9;

        TreeNode t10 = new TreeNode(10);
        t7.right = t10;

        TreeOperation treeOperation = new TreeOperation();
//        int res = treeOperation.addLeft(t1,false);

//        System.out.println(JSON.toJSONString(res));
        TreeNode res = treeOperation.getTargetCopy(t1,t6,t4);
        System.out.println(res.val);

    }

    /**
     * 计算给定二叉树的所有左叶子之和
     * @param root
     * @return
     */
    public int sumOfLeftLeaves(TreeNode root) {
        if(root == null){
            return 0;
        }
        int i = addLeft(root, false);
        return i;
    }

    /**
     * 递归累加
     * @param t
     * @param isLeft
     * @return
     */
    public int addLeft(TreeNode t ,boolean isLeft){
        int res = 0;
        //获取左叶子节点
        if(isLeft && t.left==null && t.right == null){
            res += t.val;
        }

        if(t.left!=null){
            res+=addLeft(t.left,true);
        }
        if(t.right!=null){
            res+=addLeft(t.right,false);
        }


        return res;

    }


    /**
     * 给你两棵二叉树，原始树 original 和克隆树 cloned，以及一个位于原始树 original 中的目标节点 target。
     *
     * 其中，克隆树 cloned 是原始树 original 的一个 副本 。
     *
     * 请找出在树 cloned 中，与 target 相同 的节点，并返回对该节点的引用
     *
     * 直接遍历树  同时遍历
     *
     * @param original
     * @param cloned
     * @param target
     * @return
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {

        TreeNode res = null;
        if(original.equals(target)){
            res = cloned;
        }else{
            if(original.left!=null){
                res = getTargetCopy(original.left,cloned.left,target);
                if(res!=null){
                    return res;
                }
            }
            if(original.right!=null){
                res = getTargetCopy(original.right,cloned.right,target);
                if(res!=null){
                    return res;
                }
            }

        }

        return res;
    }




}
