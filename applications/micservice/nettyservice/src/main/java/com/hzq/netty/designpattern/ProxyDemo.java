package com.hzq.netty.designpattern;

import java.lang.annotation.*;
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

    //带实现代理
    public interface ISubject{
        void request();
    }

    @Documented
    @Target(ElementType.METHOD)
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MyInterface{
        public enum Type{
            QUERY,ADD,UPDATE,DELETE;
        }

        String key() default "key";
        Type option() default Type.QUERY;
    }

    //无实现代理  mapper伪代码
    public interface ISubjectNon{
        @MyInterface(option = MyInterface.Type.QUERY)
        Object getName(String s);

        @MyInterface(key = "addr",option = MyInterface.Type.DELETE)
        Object getAddr(String s);
    }

    public class SubjectImpl implements ISubject{

        @Override
        public void request() {
            System.out.println("请求数据");
        }
    }

    public static void main(String[] args) {

        //接口实现代理测试
        /*ProxyDemo proxyDemo = new ProxyDemo();
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

        System.out.println(proxyDemo.string);*/


        //接口没有实现代理测试  mapper伪代码
        Object o = Proxy.newProxyInstance(ProxyDemo.class.getClassLoader(), new Class[]{ISubjectNon.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                System.out.println("代理开始");
                MyInterface declaredAnnotation = method.getDeclaredAnnotation(MyInterface.class);

                System.out.println("搜索id为"+declaredAnnotation.key()+"的sql语句");

                if(declaredAnnotation.option().equals(MyInterface.Type.QUERY)){
                    System.out.println("执行查询操作");
                }else if(declaredAnnotation.option().equals(MyInterface.Type.DELETE)){
                    System.out.println("执行删除操作");
                }

                System.out.println("代理结束");

                return "success";
            }
        });

        System.out.println(((ISubjectNon) o).getAddr("hzq"));

    }
}
