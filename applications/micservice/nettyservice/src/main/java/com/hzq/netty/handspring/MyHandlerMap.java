package com.hzq.netty.handspring;

import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Huangzq
 * @title: MyHandlerMap
 * @projectName applications
 * @date 2020/3/13 10:39
 */
public class MyHandlerMap {

    private Map<String , Object> map = new HashMap<>();

    private List<String> classes = new ArrayList<>();
    public void scan(String pkg){
//        pkg = "";

        String path = this.getClass().getClassLoader().getResource(pkg.replaceAll("\\.","/")).getPath();

        File file = new File(path);

        for (File listFile : file.listFiles()) {
            if (listFile.isDirectory()){
                scan(pkg+"."+listFile.getName());
            }else{
                classes.add(pkg+"."+listFile.getName().replaceAll(".class",""));
            }
        }

    }

    public void init(){
        if(CollectionUtils.isEmpty(classes)){
            throw new RuntimeException("未发现可装载类");
        }

        for (String aClass : classes) {
            try {
                Class<?> aClass1 = Class.forName(aClass);

                if(aClass1.isAnnotationPresent(MyAnnotation.class)){
                    MyAnnotation declaredAnnotation = aClass1.getDeclaredAnnotation(MyAnnotation.class);
                    Object o = aClass1.newInstance();
                    map.put(declaredAnnotation.type(),o);
                }

            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    public Object getByType(String type){
        return this.map.get(type);
    }

    public static void test(String[] args) {
        MyHandlerMap myHandlerMap = new MyHandlerMap();

        myHandlerMap.scan("com.hzq.netty.handspring");

        myHandlerMap.init();

        HandlerOne handlerOne = (HandlerOne) myHandlerMap.getByType("one");

        HandlerTwo handlerTwo = (HandlerTwo) myHandlerMap.getByType("two");

        handlerOne.sayOne("hzq");

        handlerTwo.sayTwo("sultan");
    }

}
