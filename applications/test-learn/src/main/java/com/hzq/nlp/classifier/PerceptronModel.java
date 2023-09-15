package com.hzq.nlp.classifier;

import cn.hutool.core.io.IoUtil;
import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 平均感知机
 *
 * @author Huangzq
 * @description
 * @date 2023/9/14 16:10
 */
public class PerceptronModel {
    /**
     * 特征索引
     */
    Map<String, Integer> featureMap = new HashMap<>();

    /**
     * 特征解码
     */
    List<String> featureDecode = new ArrayList<>();

    /**
     * 原始数据
     */
    List<TrainData> trainDatas;

    /**
     * 特征权重
     */
    double[] weights;

    /**
     * 多版本权重累计结果，平均使用
     */
    double[] totles;

    /**
     * 训练版本
     */
    int[] versions;

    /**
     * 初始化，训练
     *
     * @param
     * @return
     * @author Huangzq
     * @date 2023/9/15 15:38
     */
    PerceptronModel(String trainPath, int maxTimes) {
        try {
            trainDatas = readDatas(trainPath);
            int version = 0;
            weights = new double[featureMap.size()];
            totles = new double[featureMap.size()];
            versions = new int[featureMap.size()];


            for (int i = 0; i < maxTimes; i++) {
                for (TrainData trainData : trainDatas) {
                    version++;
                    int y = trainData.y;
                    int decode = decode(trainData.features);
                    if (y != decode) {
                        update(trainData.features, y, version);
                    }
                }
            }
            average(version);
            System.out.printf("训练数据量%s，特征数量%s，版本%s", trainDatas.size(), featureMap.size(), version);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 评估
     *
     * @param
     * @return
     * @author Huangzq
     * @date 2023/9/15 15:39
     */
    public void evaluate(List<TrainData> trainDatas) {
        int TP = 0, FP = 0, FN = 0;
        for (TrainData trainData : trainDatas) {
            int y = decode(trainData.features);
            if (y == 1) {
                if (trainData.y == 1) {
                    ++TP;
                } else {
                    ++FP;
                }
            } else if (trainData.y == 1) {
                ++FN;
            }
        }
        float p = TP / (float) (TP + FP) * 100;
        float r = TP / (float) (TP + FN) * 100;

        System.out.println(String.format("P=%.2f R=%.2f F1=%.2f", p, r, 2 * p * r / (p + r)));
    }

    /**
     * 预测
     *
     * @param
     * @return
     * @author Huangzq
     * @date 2023/9/15 15:39
     */
    public int predict(String text) {
        TrainData trainData = featureExtract(text, 0);
        return decode(trainData.features);
    }

    /**
     * 权重平均
     *
     * @param
     * @return
     * @author Huangzq
     * @date 2023/9/15 15:39
     */
    public void average(int version) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = (totles[i] + (version - versions[i]) * weights[i]) / version;
        }
    }

    /**
     * 感知机权重更新算法
     *
     * @param
     * @return
     * @author Huangzq
     * @date 2023/9/15 15:40
     */
    public void update(List<Integer> features, double value, int current) {
        for (Integer index : features) {
            int passed = current - versions[index];
            totles[index] += passed * weights[index];
            weights[index] += value;
            versions[index] = current;
        }

    }

    /**
     * 特征解码
     *
     * @param
     * @return
     * @author Huangzq
     * @date 2023/9/15 15:40
     */
    public int decode(List<Integer> features) {
        double y = 0;
        for (Integer feature : features) {
            y += weights[feature];
        }

        return y < 0 ? -1 : 1;
    }

    /**
     * 训练集读取
     *
     * @param
     * @return
     * @author Huangzq
     * @date 2023/9/15 15:40
     */
    public List<TrainData> readDatas(String trainPath) throws FileNotFoundException {
        List<String> datas = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream resourceAsStream = classLoader.getResourceAsStream(trainPath);
        IoUtil.readLines(resourceAsStream, StandardCharsets.UTF_8, datas);
        if (CollectionUtils.isEmpty(datas)) {
            System.out.printf("%s数据为空\n", trainPath);
            return new ArrayList<>();
        }

        List<TrainData> trainDatas = new ArrayList<>();
        for (String data : datas) {
            String[] split = data.split(",");
            TrainData trainData = featureExtract(split[0], "男".equals(split[1]) ? 1 : -1);
            trainDatas.add(trainData);
        }
        return trainDatas;
    }

    /**
     * 特征提取
     *
     * @param
     * @return
     * @author Huangzq
     * @date 2023/9/15 15:41
     */
    public TrainData featureExtract(String text, int y) {
        if (text.length() <= 2) {
            text = "_" + text.substring(text.length() - 1);
        } else {
            text = text.substring(text.length() - 2);
        }

        char[] chars = text.toCharArray();

        int k1 = put("1_" + chars[0]);
        int k2 = put("2_" + chars[1]);

        ArrayList<Integer> keys = Lists.newArrayList(k1, k2);

        return new TrainData(keys, y);
    }

    /**
     * 特征存储
     *
     * @param
     * @return
     * @author Huangzq
     * @date 2023/9/15 15:41
     */
    public int put(String feature) {
        Integer index = featureMap.get(feature);
        if (index == null) {
            index = featureMap.size();
            featureMap.put(feature, index);
            featureDecode.add(feature);
        }
        return index;
    }

    /**
     * 特征结构化
     *
     * @param
     * @author Huangzq
     * @return
     * @date 2023/9/15 15:41
     */
    class TrainData {
        public List<Integer> features;
        public int y;

        public TrainData(List<Integer> features, int y) {
            this.features = features;
            this.y = y;
        }
    }


}
