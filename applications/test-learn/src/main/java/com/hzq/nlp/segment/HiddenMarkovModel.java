package com.hzq.nlp.segment;

import com.hzq.nlp.util.RandomSelectUtil;
import com.xiaoleilu.hutool.json.JSONUtil;

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

    /**
     * 维特比算法
     *
     * @param source     观测序列
     * @param pi         初始概率
     * @param transition 转移概率矩阵
     * @param emission   发射概率矩阵
     * @return 回溯路径
     * @author Huangzq
     * @date 2023/9/16 14:28
     */
    public static int[] viterbi(int[] source, double[] pi, double[][] transition, double[][] emission) {
        int[] path = new int[source.length];

        //前状态
        double[] prefix = null;

        for (int i = 0; i < source.length; i++) {
            //当前显
            int curDisplay = source[i];
            //当前每个状态最大可能概率
            double[] curStates = new double[pi.length];

            if (i == 0) {
                for (int j = 0; j < pi.length; j++) {
                    curStates[j] = pi[j] * emission[j][curDisplay];
                }
            } else {
                //
                for (int j = 0; j < pi.length; j++) {
                    double[] curStatesTmp = new double[pi.length];
                    for (int k = 0; k < prefix.length; k++) {
                        //
                        curStatesTmp[k] = prefix[k] * transition[k][j] * emission[k][curDisplay];
                    }
                    curStates[j] = maxProbably(curStatesTmp);
                }
            }

            path[i] = maxState(curStates);
            prefix = curStates;
        }
        return path;
    }

    private static int maxState(double[] states) {
        double max = 0;
        int index = 0;
        for (int i = 0; i < states.length; i++) {
            if (max < states[i]) {
                max = states[i];
                index = i;
            }
        }
        return index;
    }

    private static double maxProbably(double[] states) {
        double max = 0;
        for (double state : states) {
            max = Math.max(max, state);
        }
        return max;
    }

//    private static double maxToState(double[]){}

    public static void main(String[] args) {
        /*sampleCreate(10, 5);

        for (int i = 0; i < 5; i++) {
            System.out.printf("\r计数：%s", i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/

        int[] source = new int[]{2, 1, 2, 0};
        int[] viterbi = viterbi(source, pi, transition, emission);
        System.out.printf("\r结果：%s", JSONUtil.toJsonStr(viterbi));

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
