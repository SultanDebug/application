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

        TreeOperation treeOperation = new TreeOperation();
        int res = treeOperation.addLeft(t1,false);

        System.out.println(JSON.toJSONString(res));

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

    public int addLeft(TreeNode t ,boolean isLeft){
        int res = 0;
        if(isLeft){
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


}
