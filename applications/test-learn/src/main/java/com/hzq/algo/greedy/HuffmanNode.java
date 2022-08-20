package com.hzq.algo.greedy;

import lombok.Data;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/13 15:55
 */
@Data
public class HuffmanNode {
    public HuffmanNode parent;
    public HuffmanNode left;
    public HuffmanNode right;
    public int weight;
    public String val;

    public String code;

    public HuffmanNode(int weight, String val) {
        this.weight = weight;
        this.val = val;
    }

    public HuffmanNode() {}
}
