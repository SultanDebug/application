package com.hzq.nlp.util;

import com.hzq.nlp.struct.TrieTreeByMap;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import org.apache.lucene.util.RamUsageEstimator;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author Huangzq
 * @description
 * @date 2023/6/7 11:01
 */
public class MemorySizeTool {
    public static String getSizeMb(Object obj){
        System.setProperty("java.vm.name","Java HotSpot(TM) ");
        long objectSize = ObjectSizeCalculator.getObjectSize(obj);
        BigDecimal o = new BigDecimal(objectSize);
        BigDecimal dvd = new BigDecimal(1024);
        BigDecimal divide = o.divide(dvd, 2, RoundingMode.HALF_UP).divide(dvd, 2, RoundingMode.HALF_UP);
        return divide+"Mb";
    }


    public static String getHumanSize(Object obj){
        return RamUsageEstimator.humanSizeOf(obj);
    }
}
