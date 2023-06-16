package com.hzq.nlp;

import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hzq.nlp.struct.TrieTreeByMap;
import com.hzq.nlp.util.MemorySizeTool;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * @author Huangzq
 * @description
 * @date 2023/6/5 09:53
 */
public class Segment {

    public static List<String> fullSegment(String str){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j =i; j < str.length(); j++) {
                String term = str.substring(i, j+1);
                if(TrieTreeByMap.isTerm(term)){
                    res.add(term);
                }
            }
        }
        return res;
    }

    public static List<String> forwardSegment(String str){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < str.length(); ) {
            String longest = str.substring(i, i+1);
            for (int j =i; j < str.length(); j++) {
                String term = str.substring(i, j+1);

                if(TrieTreeByMap.isTerm(term) && term.length()>longest.length()){
                    longest = term;
                }
            }
            if(StringUtils.isNotBlank(longest)){
                res.add(longest);
            }
            i+=longest.length();
        }
        return res;
    }


    public static List<String> backwordSegment(String str){
        List<String> res = new ArrayList<>();
        for (int i = str.length(); i > 0; ) {
            String longest = str.substring(i-1, i);
            for (int j =i; j > 0; j--) {
                String term = str.substring(j-1,i);

                if(TrieTreeByMap.isTerm(term) && term.length()>longest.length()){
                    longest = term;
                }
            }
            if(StringUtils.isNotBlank(longest)){
                res.add(longest);
            }
            i-=longest.length();
        }
        return res;
    }


    public static List<String> tierByMapSegment(String str){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            for (int j =i; j < str.length(); j++) {
                String term = str.substring(i, j+1);
                int check = TrieTreeByMap.check(term);
                if(check==-1){
                    break;
                }else if(check==0){
                    continue;
                }else {
                    res.add(term);
                }
            }
        }
        return res;
    }

    public static List<String> tierByMapForwardLongestSegment(String str){
        List<String> res = new ArrayList<>();
        for (int i = 0; i < str.length();) {
            String longest = str.substring(i, i+1);
            for (int j =i; j < str.length(); j++) {
                String term = str.substring(i, j+1);
                int check = TrieTreeByMap.check(term);
                if(check==-1){
                    break;
                }else if(check==0){
                    continue;
                }else {
                    if(term.length()>longest.length()){
                        longest = term;
                    }
                }
            }
            if(StringUtils.isNotBlank(longest)){
                res.add(longest);
            }
            i+=longest.length();
        }
        return res;
    }

    private static BinTrie<String> binTrie;
    private static DoubleArrayTrie<String> doubleArrayTrie ;

    private static void init(){
        TrieTreeByMap.init();
        Map<String, String> collect = TrieTreeByMap.datas.stream().collect(Collectors.toMap(o -> o, o -> o, (o1, o2) -> o1));
        binTrie = new BinTrie<>(collect);
        doubleArrayTrie = new DoubleArrayTrie<>(new TreeMap<>(collect));
    }
    public static List<String> binTrieSegment(String str){
        List<String> res = new ArrayList<>();
        binTrie.parseText(str,(i, i1, s) -> {res.add(str.substring(i,i1));});
        return res;
    }

    public static List<String> binTrieLongestSegment(String str){
        List<String> res = new ArrayList<>();
        binTrie.parseLongestText(str,(i, i1, s) -> {res.add(str.substring(i,i1));});
        return res;
    }

    public static List<String> DATrieSegment(String str){
        List<String> res = new ArrayList<>();
        doubleArrayTrie.parseText(str,(i, i1, s) -> {res.add(str.substring(i,i1));});
        return res;
    }

    public static List<String> DATrieLongestSegment(String str){
        List<String> res = new ArrayList<>();
        doubleArrayTrie.parseLongestText(str,(i, i1, s) -> {res.add(str.substring(i,i1));});
        return res;
    }

    public static void main(String[] args) {

        init();

        String str = "江西潘阳湖干枯 中国最大的淡水湖变成大草原 南京市长江大桥";
        System.out.println("map match all                            "+"==>"+fullSegment(str));
        System.out.println("map match forward                        "+"==>"+forwardSegment(str));
        System.out.println("map match backword                       "+"==>"+backwordSegment(str));
        System.out.println("map trie all                             "+"==>"+tierByMapSegment(str));
        System.out.println("map trie forward                         "+"==>"+tierByMapForwardLongestSegment(str));
        System.out.println("1 perfect hash rest binary query all     "+"==>"+binTrieSegment(str));
        System.out.println("1 perfect hash rest binary query forward "+"==>"+binTrieLongestSegment(str));
        System.out.println("DAT all                                  "+"==>"+DATrieSegment(str));
        System.out.println("DAT forward                              "+"==>"+DATrieLongestSegment(str));

        System.out.print("map匹配全切分:");
        runPress(str, Segment::fullSegment);
        System.out.print("map树全切分:");
        runPress(str, Segment::tierByMapSegment);
        System.out.print("数组二分查找全切分:");
        runPress(str, Segment::binTrieSegment);
        System.out.print("双数组全切分:");
        runPress(str, Segment::DATrieSegment);

        System.out.print("map匹配正向最长切分:");
        runPress(str, Segment::forwardSegment);
        System.out.print("map树正向最长切分:");
        runPress(str, Segment::tierByMapForwardLongestSegment);
        System.out.print("数组二分查找正向最长切分:");
        runPress(str, Segment::binTrieLongestSegment);
        System.out.print("双数组正向最长切分:");
        runPress(str, Segment::DATrieLongestSegment);

        System.out.println("map匹配内存："+MemorySizeTool.getSizeMb(TrieTreeByMap.datas));
        System.out.println("map树内存："+MemorySizeTool.getSizeMb(TrieTreeByMap.root));
        System.out.println("数组二分查找内存："+MemorySizeTool.getSizeMb(binTrie));
        System.out.println("双数组内存："+MemorySizeTool.getSizeMb(doubleArrayTrie));

    }

    public static void runPress(String str , Consumer<String> consumer){
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            consumer.accept(str);
        }
        long end = System.currentTimeMillis();
        long sec = end-start;
        System.out.println(((str.length()* 1000000000L)/sec)+"字/秒");
    }
}
