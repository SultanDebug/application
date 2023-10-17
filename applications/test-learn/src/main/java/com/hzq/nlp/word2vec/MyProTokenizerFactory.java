package com.hzq.nlp.word2vec;

import org.deeplearning4j.text.tokenization.tokenizer.TokenPreProcess;
import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import java.io.InputStream;

/**
 * @author Huangzq
 * @description
 * @date 2023/10/17 10:58
 */
public class MyProTokenizerFactory implements TokenizerFactory {

    @Override
    public Tokenizer create(String s) {
        MyProTokenizer myProTokenizer = new MyProTokenizer(s);
        return myProTokenizer;
    }

    @Override
    public Tokenizer create(InputStream inputStream) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TokenPreProcess getTokenPreProcessor() {
        return null;
    }

    @Override
    public void setTokenPreProcessor(TokenPreProcess tokenPreProcess) {

    }
}
