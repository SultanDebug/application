package com.hzq.nlp.classifier;

import com.hankcs.hanlp.model.perceptron.PerceptronNameGenderClassifier;
import com.hankcs.hanlp.model.perceptron.model.LinearModel;

import java.io.IOException;

/**
 * @author Huangzq
 * @description
 * @date 2023/9/13 14:47
 */
public class ClassifierModule {
    public static void main(String[] args) {
//        train();
//        predict();
        localModelTrain();
    }

    public static void localModelTrain() {
        PerceptronModel perceptronModel = new PerceptronModel("data/test.csv", 10);
        System.out.println("训练完成");

        String[] names = new String[]{"赵丽", "朱亚文", "何润东", "赵建军", "沈雁冰", "陆雪琪", "李冰冰", "黄艺甲"};
        for (String name : names) {
            System.out.printf("%s=%s\n", name, perceptronModel.predict(name));
        }

        perceptronModel.evaluate(perceptronModel.trainDatas);
    }

    /**
     * hanlp感知机训练
     *
     * @param
     * @return
     * @author Huangzq
     * @date 2023/9/15 15:37
     */
    public static void train() {
        PerceptronNameGenderClassifier classifier = new PerceptronNameGenderClassifier();
        System.out.println("训练评估值：" + classifier.train("D:\\coderepo\\spring-cloud-demo\\applications\\test-learn\\src\\main\\resources\\data\\test.csv", 1, true));

        LinearModel model = classifier.getModel();
        /*try {
            System.out.println("特征数量：" + model.parameter.length);
            model.save("模型路径", model.featureMap.entrySet(), 0, true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }

    /**
     * hanlp感知机预测
     *
     * @param
     * @return
     * @author Huangzq
     * @date 2023/9/15 15:37
     */
    public static void predict() {
        try {
            PerceptronNameGenderClassifier classifier1 = new PerceptronNameGenderClassifier("C:\\Users\\zhenqiang.huang\\Desktop\\nlp\\classifier\\model");

            String[] names = new String[]{"赵丽", "朱亚文", "何润东", "赵建军", "沈雁冰", "陆雪琪", "李冰冰", "黄艺甲"};

            for (String name : names) {

                System.out.println(name + ":" + classifier1.predict(name));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
