package com.hzq.nlp.struct;

import com.hzq.algo.search.Trie;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huangzq
 * @description
 * @date 2023/6/5 16:48
 */
public class TrieTreeByMap {

    public static class TrieNode{
        public char val;
        public boolean flag;
        public Map<Character, TrieTreeByMap.TrieNode> chailds;
    }

    public static TrieTreeByMap.TrieNode root = new TrieTreeByMap.TrieNode();
    public static List<String> datas = new ArrayList<>();

    private static Map<String,String> MAP = new HashMap<>();

    //dict
    public static boolean isTerm(String str){
        return MAP.containsKey(str);
    }

    public static int check(String res ){
        TrieTreeByMap.TrieNode tmp = root;
        char[] chars = res.toCharArray();
        for (char aChar : chars) {
            TrieTreeByMap.TrieNode trieNode = tmp.chailds.get(aChar);
            if(trieNode==null){
                return -1;
            }
            tmp = trieNode;
            if(tmp.val != aChar){
                return -1;
            }
        }

        return tmp.flag ? 1 : 0;
    }


    public static void init(){
        getData();
        root.chailds = new HashMap();
        List<String> inputs = datas;

        inputs.sort(String::compareToIgnoreCase);

        for (String input : inputs) {
            char[] chars = input.toCharArray();
            TrieTreeByMap.TrieNode pre = root;
            if(pre.chailds == null){
                pre.chailds = new HashMap<>();
            }
            for (int i = 0; i < chars.length; i++) {
                TrieTreeByMap.TrieNode t = pre.chailds.get(chars[i]);

                if(t==null){
                    t = new TrieTreeByMap.TrieNode();
                    t.val = chars[i];
                    t.flag = i==chars.length-1;
                    t.chailds = new HashMap<>();

                    pre.chailds.put(chars[i],t);
                }

                pre = t;
            }
        }

        for (String data : TrieTreeByMap.datas) {
            MAP.put(data,"");
        }
    }

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
}
