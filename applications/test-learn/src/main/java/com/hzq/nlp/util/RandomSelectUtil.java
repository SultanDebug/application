package com.hzq.nlp.util;

import com.xiaoleilu.hutool.json.JSONUtil;

import java.util.Arrays;
import java.util.Random;

/**
 * @author Huangzq
 * @description
 * @date 2023/7/8 11:24
 */
public class RandomSelectUtil {

    public static void main(String[] args) {
        randomTest();
    }

    public static void randomTest(){
        double[] probability = {0.2,0.5,0.3};
        char[] chars = {'A','B','C'};

        int[] count = {0,0,0};

        for (int i = 0; i < 10000; i++) {
            int pos = select(probability);
            count[pos]=count[pos]+1;
        }

        System.out.println(JSONUtil.toJsonStr(count));
        for (int i = 0; i < count.length; i++) {
            System.out.println(count[i]/10000.0);
        }
    }

    public static int select(double[] probability){
        double sum = Arrays.stream(probability).sum();

        Random random = new Random();
        double cur = random.nextDouble() * sum;

        double calProb = 0.0;
        for (int i = 0; i < probability.length; i++) {
            calProb += probability[i];
            if(cur < calProb){
                return i;
            }
        }

        return -1;
    }

}
