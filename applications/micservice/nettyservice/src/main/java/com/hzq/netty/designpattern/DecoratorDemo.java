package com.hzq.netty.designpattern;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huangzq
 * @title: DecoratorDemo
 * @projectName applications
 * @date 2020/4/7 15:57
 */
public class DecoratorDemo {

    public interface IClothis{
        void dress(String para);
        void unDress(String para);
    }

    public class Clothis implements IClothis{
        public Map<String,String> clothiss = new LinkedHashMap<>();
        @Override
        public void dress(String para) {
            clothiss.put(para,para);
        }

        @Override
        public void unDress(String para) {
            clothiss.remove(para);
        }
    }


    public class EClothis extends Clothis{

    }

}
