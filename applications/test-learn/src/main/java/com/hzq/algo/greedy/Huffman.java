package com.hzq.algo.greedy;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 哈夫曼编码：数据压缩
 * 1.频率越高  编码越短
 * 2.不能有二义性 前缀码特性
 * @author Huangzq
 * @description
 * @date 2022/8/13 14:00
 */
public class Huffman {
    private HuffmanNode head = null;

    private List<HuffmanNode> arrays = Lists.newArrayList();

    private List<HuffmanNode> outArrs = Lists.newArrayList();

    public static void main(String[] args) {
        Huffman huffman = new Huffman();
        huffman.createTree(
                new int[]{1,2,3,4,5}
                ,new String[]{"我","是","中","国","人"});

        for (HuffmanNode array : huffman.outArrs) {
            System.out.println(array.val+"编码是："+huffman.getHuffmanCode(array));
//            System.out.println(array.val+"编码是："+huffman.getHuffmancodeByWeight(array.weight));
        }
    }

    /**
     * Description:
     *  创建哈夫曼树
     *  1.根据权重排序
     *  2.以最小的两节点组成一颗子树，权重为节点和，权重小的为左节点、编码为0，大的为右节点、编码为1
     *  3.子树根节点加入列表，移除最小两个子节点，重新排序，循环创建、移除，知道列表只有一个根节点
     * @param
     * @return
     * @author Huangzq
     * @date 2022/8/24 15:21
     */
    public void createTree(int [] weights, String[] values){
        for (int i = 0; i < weights.length; i++) {
            HuffmanNode node = new HuffmanNode(weights[i],values[i]);
            arrays.add(node);
            outArrs.add(node);
        }
        arrays.sort(Comparator.comparingInt(HuffmanNode::getWeight));

        while (true){
           if(arrays.size()<=1){break;}
           HuffmanNode left = arrays.get(0);
           HuffmanNode right = arrays.get(1);
           int tol = left.weight+right.weight;

           HuffmanNode tmp = new HuffmanNode(tol,null);
           tmp.left = left;
           tmp.right = right;
           left.parent = tmp;
           left.code = "0";
           right.parent = tmp;
           right.code = "1";
           head = tmp;

           arrays.remove(left);
           arrays.remove(right);
           arrays.add(tmp);
           arrays.sort(Comparator.comparingInt(HuffmanNode::getWeight));
        }
    }

    public String getHuffmanCode(HuffmanNode val){
        HuffmanNode tmp = val;
        StringBuilder res = new StringBuilder();
        while (true){
            if(tmp==head){
                return res.toString();
            }
            res.insert(0, tmp.code);
            tmp = tmp.parent;
        }
    }


    public String getHuffmancodeByWeight(int weight){
        HuffmanNode tmp = head;
        StringBuilder res = new StringBuilder();
        while (true){
            if(tmp.left==null && tmp.right==null &&tmp.weight == weight){
                res.append(tmp.code);
                return res.toString();
            }
            if(tmp.left == null && tmp.right == null){
                throw new RuntimeException("未找到叶子节点");
            }

            tmp =  weight < tmp.getWeight() ? tmp.left : tmp.right;
        }
    }
}
