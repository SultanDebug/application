package com.hzq.nlp.word2vec;

import lombok.extern.slf4j.Slf4j;
import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.deeplearning4j.text.sentenceiterator.LineSentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentenceIterator;
import org.deeplearning4j.text.sentenceiterator.SentencePreProcessor;
import org.deeplearning4j.text.tokenization.tokenizer.Tokenizer;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;

import java.io.File;
import java.util.Arrays;
import java.util.Collection;

/**
 * @author Huangzq
 * @description
 * @date 2023/10/16 17:32
 */
@Slf4j
public class DeeplearningEmbeddingTest {
    private static Word2Vec word2Vec = null;

    public static void main(String[] args) {
        //训练
        deeplearningTrain();

        //加载
        deeplearningLoad();

        //预测
        deeplearningPredict();

        //测试
        deeplearningTest();
    }


    public static void deeplearningTest() {
        /*String s = StringCleaning.stripPunct("王者荣耀");
        System.out.println(s);*/

        Tokenizer defaultTokenizer = new MyProTokenizer("王者荣耀");
        while (defaultTokenizer.hasMoreTokens()) {
            log.info(defaultTokenizer.nextToken());
        }
    }

    public static void deeplearningTrain() {
        //        HanLP.segment()
//        InputStream resourceAsStream = EmbeddingTest.class.getClassLoader().getResourceAsStream("data/brand.csv");
        String path = DeeplearningEmbeddingTest.class.getClassLoader().getResource("data/brand.csv").getPath();
        SentenceIterator iter = new LineSentenceIterator(new File(path));
        iter.setPreProcessor((SentencePreProcessor) String::toLowerCase);

        TokenizerFactory tokenizerFactory = new MyProTokenizerFactory();
//        tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());
        word2Vec = new Word2Vec.Builder()
                .minWordFrequency(5)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .iterate(iter)
                .tokenizerFactory(tokenizerFactory)
                .build();
        word2Vec.fit();

        for (int i = 0; i < 10; i++) {
            log.info("第{}次训练", i);
            updateTrain();
        }

        WordVectorSerializer.writeWord2VecModel(word2Vec, "embedding.txt");

    }

    public static void updateTrain() {
        String path = DeeplearningEmbeddingTest.class.getClassLoader().getResource("data/brand.csv").getPath();
        SentenceIterator iter = new LineSentenceIterator(new File(path));
        iter.setPreProcessor((SentencePreProcessor) String::toLowerCase);

        TokenizerFactory tokenizerFactory = new MyProTokenizerFactory();

        word2Vec.setTokenizerFactory(tokenizerFactory);
        word2Vec.setSentenceIterator(iter);

        word2Vec.fit();
    }

    public static void deeplearningLoad() {
        TokenizerFactory tokenizerFactory = new MyProTokenizerFactory();
//        tokenizerFactory.setTokenPreProcessor(new CommonPreprocessor());
        word2Vec = new Word2Vec.Builder()
                .minWordFrequency(5)
                .layerSize(100)
                .seed(42)
                .windowSize(5)
                .tokenizerFactory(tokenizerFactory)
                .build();
        word2Vec = WordVectorSerializer.readWord2VecModel("embedding.txt");
    }

    public static void deeplearningPredict() {
        Collection<String> strings = word2Vec.wordsNearest(Arrays.asList("王者", "荣耀"), Arrays.asList(), 10);

        for (String string : strings) {
            System.out.printf("[" + string + "] ");
        }
        System.out.println();
    }
}
