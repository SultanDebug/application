package com.hzq.algo.greedy;

import com.xiaoleilu.hutool.json.JSONUtil;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author Huangzq
 * @description
 * @date 2022/8/8 09:13
 */
public class NShortestPath {

    // 祖国统一是不可阻挡的

    public static Map<String, Integer> map = new HashMap<>();

    static {
        map.put("祖国", 1);
        map.put("统一", 1);
        map.put("不可", 1);
        map.put("阻挡", 1);
        map.put("不可阻挡", 1);
    }

    NSPNode root = new NSPNode("root", 0, 0, false,0,0);

    static List<List<NSPNode>> seg = new ArrayList<>();

    static int totle = 1;
    static int row = 0;
    static int col = 0;

    public static void init(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            List<NSPNode> list = new ArrayList<>();
            StringBuilder builder = new StringBuilder();
            builder.append(chars[i]);
            list.add(new NSPNode(builder.toString(), 1, 1, i == str.length() - 1,i,totle));
            row++;
            totle++;
            for (int j = i + 1; j < str.length(); j++) {
                builder.append(chars[j]);
                String sBuild = builder.toString();
                if (map.containsKey(sBuild)) {
                    list.add(new NSPNode(sBuild, 1, j - i + 1, j == str.length() - 1,i,totle));
                    totle++;
                }
            }
            col = Math.max(col,list.size());
            seg.add(list);
        }
    }

    public static void main(String[] args) {
        NShortestPath.init("祖国统一是不可阻挡");

        for (List<NSPNode> list : seg) {
            System.out.println(JSONUtil.toJsonStr(list));
        }

        int[][] gragh = createGragh();

        System.out.println(gragh);

        /*List<Term> list = HanLP.segment("祖国统一是不可阻挡的");
        System.out.println(list);*/

    }

    public static int[][] createGragh() {
        int[][] nodes = new int[totle][totle];

        if(CollectionUtils.isEmpty(seg)){
            return null;
        }

        for (int i = 0; i < seg.size(); i++) {
            List<NSPNode> rowNSPNodes = seg.get(i);
            for (int j = 0; j < rowNSPNodes.size(); j++) {
                NSPNode colNodes = rowNSPNodes.get(j);
                nodes[colNodes.pos][colNodes.pos] = 2;
                if(!colNodes.stop){
                    for (NSPNode NSPNode : seg.get(colNodes.level + colNodes.dist)) {
                        nodes[colNodes.pos][NSPNode.pos] = 1;
                    }
                }
            }
        }
        return nodes;
    }

    public void getPath(int[][] gragh) {
        int [] dist = new int[gragh.length+1];
        dist[1] = 1;



        for (int i = 1; i < gragh.length; i++) {

        }
    }
}
