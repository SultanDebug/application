package com.hzq.nlp.hantest;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.collection.trie.DoubleArrayTrie;
import com.hankcs.hanlp.collection.trie.bintrie.BinTrie;
import com.hankcs.hanlp.model.crf.CRFLexicalAnalyzer;
import com.hankcs.hanlp.model.perceptron.PerceptronLexicalAnalyzer;
import com.hankcs.hanlp.seg.Dijkstra.DijkstraSegment;
import com.hankcs.hanlp.seg.NShort.NShortSegment;
import com.hankcs.hanlp.seg.Other.AhoCorasickDoubleArrayTrieSegment;
import com.hankcs.hanlp.seg.Other.DoubleArrayTrieSegment;
import com.hankcs.hanlp.seg.Segment;
import com.hankcs.hanlp.seg.SegmentPipeline;
import com.hankcs.hanlp.seg.Viterbi.ViterbiSegment;
import com.hankcs.hanlp.seg.common.Term;
import com.hzq.nlp.struct.TrieTreeByMap;
import com.hzq.nlp.util.MemorySizeTool;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.apache.lucene.util.RamUsageEstimator;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * @author Huangzq
 * @description
 * @date 2023/6/7 09:35
 */
public class TrieTest {

    public static void main(String[] args) {

        /*TreeMap<String, String> treeMap = new TreeMap<>();
        treeMap.put("中国","3");
        treeMap.put("中肯","1");
        treeMap.put("安抚","2");


        DoubleArrayTrie<String> trie = new DoubleArrayTrie<>(treeMap);
        int transition = trie.transition("中国", 0);
        boolean containsKey = trie.containsKey("中国");
        String s = trie.get("中肯");
        System.out.println(s);*/


        DijkstraSegment segment = new DijkstraSegment();
        HanLP.Config.enableDebug(true);
        List<Term> seg = segment.seg("JOURNAL OF FIELD ORNITHOLOGY 1008-5602 孟唯娟 自动化技术、计算机技术 34-1205/TP TN91 Merrick, G. S. ＣＡＤ技术以及ＭＣＭ的电学分析");

        System.out.println(seg);

//        PerceptronLexicalAnalyzer perceptronLexicalAnalyzer = new PerceptronLexicalAnalyzer();
//        CRFLexicalAnalyzer crfLexicalAnalyzer = new CRFLexicalAnalyzer();
//        NShortSegment nShortSegment = new NShortSegment();
//        DoubleArrayTrieSegment doubleArrayTrieSegment = new DoubleArrayTrieSegment();
        ViterbiSegment viterbiSegment = new ViterbiSegment();
        viterbiSegment.enableOffset(true);
        viterbiSegment.enableAllNamedEntityRecognize(false);
        viterbiSegment.enableIndexMode(false);


        String s = "磷脂酸和磷脂酰乙醇胺";
//        List<Term> seg1 = perceptronLexicalAnalyzer.seg(s);
//        List<Term> seg2 = crfLexicalAnalyzer.seg(s);
//        List<Term> seg3 = nShortSegment.seg(s);
//        List<Term> seg4 = doubleArrayTrieSegment.seg(s);
        List<Term> seg5 = viterbiSegment.seg(s);

//        System.out.println(seg1);
//        System.out.println(seg2);
//        System.out.println(seg3);
//        System.out.println(seg4);
        System.out.println(seg5);


    }

    public static void memsize() {
        Map<String, String> collect = TrieTreeByMap.datas.stream().collect(Collectors.toMap(o -> o, o -> o, (o1, o2) -> o1));
        BinTrie<String> binTrie = new BinTrie<>(collect);

        System.out.println(MemorySizeTool.getSizeMb(TrieTreeByMap.datas));
        System.out.println(MemorySizeTool.getSizeMb(TrieTreeByMap.root));
        System.out.println(MemorySizeTool.getSizeMb(binTrie));

        System.out.println(binTrie.containsKey("china"));


        DoubleArrayTrie<String> doubleArrayTrie = new DoubleArrayTrie<>(new TreeMap<>(collect));
        System.out.println(MemorySizeTool.getSizeMb(doubleArrayTrie));
    }
}
