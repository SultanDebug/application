package com.hzq.algo.greedy;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/6 11:40
 */
@Data
public class Node {
    public String value;
    public int weight;
    public boolean stop = false;
    public int dist;

    //数组图参数
    public int level;
    public int pos;

    //树图参数
    public Node parent;
    public List<Node> next;

    public Node() {
    }

    public Node(String value, int weight , int dist, boolean stop , int levle, int pos) {
        this.level = levle;
        this.pos = pos;
        this.stop = stop;
        this.dist = dist;
        this.value = value;
        this.weight = weight;
    }
}
