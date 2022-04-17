package com.hzq.common.interceptor.feign.module;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @Desc TODO
 * @Author Huang
 * @Date 2022/4/17 14:35
 **/
//@Slf4j
@Component
public class MyRpcReferenceListener implements ApplicationListener<ContextRefreshedEvent>, ApplicationContextAware {
    private static final Logger log = LoggerFactory.getLogger(MyRpcReferenceListener.class);
    public static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        MyRpcReferenceListener.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("开始自动注入rpc代理对象");

        try {
            List<Class<?>> classes = scanPkg();
            for (Class<?> aClass : classes) {
                Field[] declaredFields = aClass.getDeclaredFields();
                for (Field declaredField : declaredFields) {
                    MyRpcReference declaredAnnotation = declaredField.getAnnotation(MyRpcReference.class);
                    if (null != declaredAnnotation) {
                        Class<?> type = declaredField.getType();
                        RpcProxy proxy = new RpcProxy(declaredAnnotation.host(), declaredAnnotation.port());
                        Object o = Proxy.newProxyInstance(aClass.getClassLoader(), new Class[]{type}, proxy);
                        declaredField.setAccessible(true);
                        Object bean = applicationContext.getBean(aClass);
                        declaredField.set(bean, o);
                        log.info("对象：{}注入实例：{} 成功", aClass.getName(), o.getClass().getName());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        log.info("自动注入rpc代理对象完毕");
    }

    private static List<Class<?>> scanPkg() throws ClassNotFoundException {
        String path = "com.hzq";
        String url = path.replaceAll("\\.", "/");

        URL resource = MyRpcReferenceListener.class.getClassLoader().getResource(url);
        assert resource != null;
        File file = new File(resource.getFile());

        List<String> paths = new ArrayList<>();
        getClasses(file, paths);
        List<Class<?>> classes = new ArrayList<>();

        for (String s : paths) {
            String realPath = s.substring(file.getPath().length())
                    .replaceAll("\\\\", ".")
                    .replaceAll("\\.class", "");
            Class<?> aClass = Class.forName(path + realPath);
            classes.add(aClass);
        }
        return classes;
    }

    private static void getClasses(File file, List<String> res) {
        if (file.isFile()) {
            res.add(file.getPath());
        }

        File[] files = file.listFiles();

        if (null != files && files.length > 0) {
            for (File listFile : files) {
                getClasses(listFile, res);
            }
        }
    }
}
