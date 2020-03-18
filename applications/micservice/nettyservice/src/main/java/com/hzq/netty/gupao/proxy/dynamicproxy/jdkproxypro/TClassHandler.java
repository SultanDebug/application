package com.hzq.netty.gupao.proxy.dynamicproxy.jdkproxypro;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author Huangzq
 * @title: TClassHandler
 * @projectName applications
 * @date 2020/3/18 17:26
 */
public class TClassHandler<T> implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        this.before();

//        Object o = method.invoke(target,args);

        this.after();

        return null;
    }

    private void before(){
        System.out.println("invoke before ............");
    }

    private void after(){
        System.out.println("invoke after ............");
    }
}
