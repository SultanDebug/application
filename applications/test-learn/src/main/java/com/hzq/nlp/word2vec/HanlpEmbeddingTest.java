package com.hzq.nlp.word2vec;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.mining.word2vec.DocVectorModel;
import com.hankcs.hanlp.mining.word2vec.Word2VecTrainer;
import com.hankcs.hanlp.mining.word2vec.WordVectorModel;
import com.hankcs.hanlp.seg.common.Term;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Huangzq
 * @description
 * @date 2023/10/16 17:32
 */
@Slf4j
public class HanlpEmbeddingTest {
    private static WordVectorModel word2Vec = null;

    private static DocVectorModel docVectorModel = null;
    private static Map<Integer, String> index = new HashMap<>();
    private static String trainFile = DeeplearningEmbeddingTest.class.getClassLoader().getResource("data").getPath() + "/brand.csv";
    private static String moduleFile = "module.txt";
    private static String processFile = "brand-process.txt";

    public static void main(String[] args) {
        //预处理
//        preProcess();

        //训练
//        hanlpTrain(processFile);

        //加载
        hanlpLoad();

        //预测
        hanlpPredict();
    }

    private static void preProcess() {

//        String path = DeeplearningEmbeddingTest.class.getClassLoader().getResource("data").getPath();

        try {
            FileReader reader = new FileReader(trainFile);
            BufferedReader bufferedReader = new BufferedReader(reader);

            FileWriter writer = new FileWriter(processFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            String line = bufferedReader.readLine();
            while (line != null) {
                List<Term> segment = HanLP.segment(line);
                String join = StringUtils.join(segment.stream().map(term -> term.word).collect(Collectors.toList()), " ");
                bufferedWriter.write(join);
                bufferedWriter.newLine();
                line = bufferedReader.readLine();
            }
            bufferedWriter.flush();

            bufferedReader.close();
            bufferedWriter.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void hanlpTrain(String path) {
        Word2VecTrainer trainer = new Word2VecTrainer();
        word2Vec = trainer.train(path, moduleFile);
        docVectorModel = new DocVectorModel(word2Vec);
        docVectorModel.setSegment(HanLP.newSegment());
        try {
            FileReader reader = new FileReader(trainFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            int id = 0;
            while (line != null) {
                docVectorModel.addDocument(id, line);
                index.put(id, line);
                id++;
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void hanlpLoad() {
        try {
            word2Vec = new WordVectorModel(moduleFile);
            docVectorModel = new DocVectorModel(word2Vec);
            docVectorModel.setSegment(HanLP.newSegment());

            FileReader reader = new FileReader(trainFile);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = bufferedReader.readLine();
            int id = 0;
            while (line != null) {
                docVectorModel.addDocument(id, line);
                index.put(id, line);
                id++;
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void hanlpPredict() {
        for (Map.Entry<String, Float> entry : word2Vec.nearest("王者荣耀")) {
            System.out.printf("%50s\t\t%f\n", entry.getKey(), entry.getValue());
        }

        for (Map.Entry<Integer, Float> entry : docVectorModel.nearest("王者荣耀")) {
            System.out.printf("%50s\t\t%f\n", index.get(entry.getKey()), entry.getValue());
        }
    }
}
