package com.hzq.algo.search;

import com.xiaoleilu.hutool.json.JSONObject;
import com.xiaoleilu.hutool.json.JSONUtil;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huangzq
 * @description
 * @date 2022/9/16 19:45
 */
public class Trie {
    public static class TrieNode{
        public char val;
        public boolean flag;
        public Map<Character,TrieNode> chailds;
    }

    public static TrieNode root = new TrieNode();

    public static void main(String[] args) {
        long start = System.nanoTime();
        getData();
        long end1 = System.nanoTime();
        System.out.println("加载结束："+(end1-start));
        init();
        long end2 = System.nanoTime();
        System.out.println("初始化结束："+(end2-end1));
        List<String> res = new ArrayList<>();
//        traverse("",res,root);
//        System.out.println(res);
        String check = check("黄建群");
        long end3 = System.nanoTime();
        long l1 = end3 - end2;
        System.out.println("trie树查询结束："+l1);
        System.out.println(check);

        String checkByList = checkByList("黄建群");
        long end4 = System.nanoTime();
        long l2 = end4-end3;
        System.out.println("暴力查询结束："+l2);
        System.out.println("暴力倍数："+(l2/l1));
        System.out.println(check);
    }
    static List<String> datas = new ArrayList<>();
    public static void getData(){
        try {
            InputStream in = Trie.class.getResourceAsStream("/npathword.data");
            InputStreamReader inReader = new InputStreamReader(in, StandardCharsets.UTF_8);
            BufferedReader bufReader = new BufferedReader(inReader);

            String line = null;
            int i = 1;
            while((line = bufReader.readLine()) != null){
                datas.add(line);
                i++;
            }
            bufReader.close();
            inReader.close();
            in.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String checkByList(String res){
        boolean b = datas.stream().anyMatch(o -> o.equals(res));
        return b ? res : "none";
    }

    public static String check(String res ){
        TrieNode tmp = root;
        char[] chars = res.toCharArray();
        for (char aChar : chars) {
            TrieNode trieNode = tmp.chailds.get(aChar);
            if(trieNode==null){
                return "none";
            }
            tmp = trieNode;
            if(tmp.val != aChar){
                return "none";
            }
        }

        if(!tmp.flag){
            return "none";
        }

        return res;
    }

    public static String check(String res ,String search, TrieNode node){
        for (Map.Entry<Character, TrieNode> characterTrieNodeEntry : node.chailds.entrySet()) {
            StringBuilder tmp = new StringBuilder(search);
            Character c = characterTrieNodeEntry.getKey();
            TrieNode t = characterTrieNodeEntry.getValue();
            tmp.append(c.charValue());

            if(t.flag && res.equals(tmp.toString())){
                return res;
            }else{
                String s = check(res,tmp.toString(),t);
                if(s.equals(res)){return res;}
            }
        }
        return "none";
    }

    public static void traverse(String res , List<String> list, TrieNode node){
        for (Map.Entry<Character, TrieNode> characterTrieNodeEntry : node.chailds.entrySet()) {
            StringBuilder tmp = new StringBuilder(res);
            Character c = characterTrieNodeEntry.getKey();
            TrieNode t = characterTrieNodeEntry.getValue();
            tmp.append(c.charValue());

            if(t.flag || t.chailds.isEmpty()){
                list.add(tmp.toString());
            }else{
                traverse(tmp.toString(),list,t);
            }
        }
    }

    public static void init(){
        root.chailds = new HashMap();
        List<String> inputs = datas;

        TrieNode[] nodes = null;

        inputs.sort(String::compareToIgnoreCase);

        for (String input : inputs) {
            char[] chars = input.toCharArray();
            TrieNode[] tmpNodes = new TrieNode[chars.length];
            TrieNode pre = root;
            if(pre.chailds == null){
                pre.chailds = new HashMap<>();
            }
            for (int i = 0; i < chars.length; i++) {
                TrieNode t = null;
                if(nodes == null){
                    t = new TrieNode();
                    t.val = chars[i];
                    t.flag = i==chars.length-1;
                    t.chailds = new HashMap<>();

                    pre.chailds.put(chars[i],t);
                }else{
                    if(i >= nodes.length || chars[i] != nodes[i].val){
                        t = new TrieNode();
                        t.val = chars[i];
                        t.flag = i==chars.length-1;
                        t.chailds = new HashMap<>();
                        pre.chailds.put(chars[i],t);
                    }else{
                        t = nodes[i];
                        t.flag = i==chars.length-1;
                    }
                }
                pre = t;
                tmpNodes[i] = t;
            }
            nodes = tmpNodes;
        }
    }
}
