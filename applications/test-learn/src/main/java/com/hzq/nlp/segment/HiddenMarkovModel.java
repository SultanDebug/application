package com.hzq.nlp.segment;

import com.hzq.nlp.util.RandomSelectUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Huangzq
 * @description
 * @date 2023/7/7 16:01
 */
public class HiddenMarkovModel {
    static double[] pi = {0.6, 0.4};
    static double[][] transition = {
            {0.7, 0.3},
            {0.4, 0.6}
    };
    static double[][] emission = {
            {0.5, 0.4, 0.1},
            {0.1, 0.3, 0.6}
    };

    public static void sampleCreate(int dataSize, int docSize) {
        List<List<String>> datas = new ArrayList<>(dataSize);

        for (int i = 0; i < dataSize; i++) {
            int hiddenState = RandomSelectUtil.select(pi);
            List<String> doc = new ArrayList<>();

            for (int j = 0; j < docSize; j++) {
                hiddenState = RandomSelectUtil.select(transition[hiddenState]);
                int displayState = RandomSelectUtil.select(emission[hiddenState]);
                doc.add(Status.valueByOrder(hiddenState).name() + "/" + Display.valueByOrder(displayState).name());
            }

            datas.add(doc);
        }


        for (List<String> data : datas) {
            System.out.println(data);
        }


    }

    public static void main(String[] args) {
        sampleCreate(10, 5);

        /*double[] a = {0.1, 0.3, 0.5, 0.7, 0.9};
        double random = Math.random();
        int index = Arrays.binarySearch(a, random);
        int index1 = index >= 0 ? index : -index - 1;
        System.out.println(random + "/" + index + "/" + index1);*/
    }


    //隐状态
    enum Status {
        Sun,
        Rain;

        public static Status valueByOrder(int order) {
            return Status.values()[order];
        }
    }


    //显状态
    enum Display {
        Walk,
        Shop,
        Sleep;

        public static Display valueByOrder(int order) {
            return Display.values()[order];
        }
    }


}
