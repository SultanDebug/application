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
                    System.out.printf("cur:%s display：%s prob:%s , calculate： %s * %s .\n"
                            , Status.valueByOrder(j).name()
                            , Display.valueByOrder(curDisplay).name()
                            , curStates[j]
                            , pi[j]
                            , emission[j][curDisplay]);
                }
            } else {

                for (int cur = 0; cur < pi.length; cur++) {
                    //cur:当前状态
                    double[] curStatesTmp = new double[pi.length];
                    for (int pre = 0; pre < pi.length; pre++) {
                        //pre：前状态
                        curStatesTmp[cur] = prefix[pre] * transition[pre][cur] * emission[cur][curDisplay];
                        System.out.printf("pre:%s cur:%s display：%s prob:%s , calculate： %s * %s * %s.\n"
                                , Status.valueByOrder(pre).name()
                                , Status.valueByOrder(cur).name()
                                , Display.valueByOrder(curDisplay).name()
                                , curStatesTmp[cur]
                                , prefix[pre]
                                , transition[pre][cur]
                                , emission[cur][curDisplay]);
                    }
                    //当前状态下，前一个最可能的状态
                    curStates[cur] = maxProbably(curStatesTmp);
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
        List<String> list = new ArrayList<>();
        for (int i : viterbi) {
            list.add(Status.valueByOrder(i).name());
        }

        List<String> list1 = new ArrayList<>();
        for (int i : source) {
            list1.add(Display.valueByOrder(i).name());
        }

        System.out.printf("\r动作：%s，结果：%s", String.join(",", list1), String.join(",", list));

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
