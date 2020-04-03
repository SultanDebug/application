package com.hzq.netty.designpattern;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Huangzq
 * @title: ProxyDemo
 * @projectName applications
 * @date 2020/4/3 15:25
 */
public class ProxyDemo {

    private String string;
    public class SubjectProxy implements InvocationHandler {

        private Object object;


        public SubjectProxy(Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("开始代理前");
            Object res = method.invoke(object,args);
            System.out.println("代理后");
            return res;
        }
    }

    public interface ISubject{
        void request();
    }

    public class SubjectImpl implements ISubject{

        @Override
        public void request() {
            System.out.println("请求数据");
        }
    }

    public static void main(String[] args) {

        ProxyDemo proxyDemo = new ProxyDemo();
        SubjectImpl subject = proxyDemo.new SubjectImpl();
        SubjectProxy subjectProxy = proxyDemo.new SubjectProxy(subject);

        ISubject iSubject = (ISubject) Proxy.newProxyInstance(ISubject.class.getClassLoader(),new Class[]{ISubject.class},subjectProxy);

        try {
            Class clazz = proxyDemo.getClass();
            Field[] fields = clazz.getDeclaredFields();
            Field field = clazz.getDeclaredField("string");
            field.setAccessible(true);
            field.set(proxyDemo,"hzq");
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        iSubject.request();

        System.out.println(proxyDemo.string);

    }
}
