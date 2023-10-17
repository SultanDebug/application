package com.hzq.nlp.word2vec;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.seg.common.Term;
import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Huangzq
 * @description
 * @date 2023/10/17 10:58
 */
public class MyProTokenizer implements Tokenizer {
    List<String> tokens;
    int index = 0;

    public MyProTokenizer(String token) {
        List<Term> segment = HanLP.segment(token);
        tokens = segment.stream().map(o->o.word).collect(Collectors.toList());
        tokens.add(token);
    }

    @Override
    public boolean hasMoreTokens() {
        return index < tokens.size();
    }

    @Override
    public int countTokens() {
        return tokens.size();
    }

    @Override
    public String nextToken() {
        return tokens.get(index++);
    }

    @Override
    public List<String> getTokens() {
        return tokens;
    }

    @Override
    public void setTokenPreProcessor(TokenPreProcess tokenPreProcess) {

    }
}
