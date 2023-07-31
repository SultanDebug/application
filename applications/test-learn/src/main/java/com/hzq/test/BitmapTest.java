package com.hzq.test;

import org.roaringbitmap.RoaringBitmap;

import java.util.Random;

/**
 * @author Huangzq
 * @description
 * @date 2023/7/18 20:52
 */
public class BitmapTest {
    public static void main(String[] args) {
        RoaringBitmap bitmap1 = new RoaringBitmap();
        RoaringBitmap bitmap2 = new RoaringBitmap();

        RoaringBitmap bitmap3 = new RoaringBitmap();
        RoaringBitmap bitmap4 = new RoaringBitmap();


        for (int i = 0; i < 100000000; i++) {
            double random1 = Math.random();
            double random2 = Math.random();
            if(random1 < 0.00001 ){
                bitmap2.add(i);
                bitmap4.add(i);
            }else if (random1 < 0.0001){
                bitmap1.add(i);
                bitmap3.add(i);
            }
        }

        System.out.println(bitmap1.getLongCardinality()+"/"+bitmap2.getLongCardinality());

        long start1 = System.nanoTime();
        bitmap1.and(bitmap2);
        long end1 = System.nanoTime();

        System.out.println(bitmap1.getLongCardinality()+"/"+bitmap1.getLongSizeInBytes()+"/"+(end1-start1));

        long start2 = System.nanoTime();
        bitmap3.or(bitmap4);
        long end2 = System.nanoTime();

        System.out.println(bitmap3.getLongCardinality()+"/"+bitmap3.getLongSizeInBytes()+"/"+(end2-start2));

    }
}
