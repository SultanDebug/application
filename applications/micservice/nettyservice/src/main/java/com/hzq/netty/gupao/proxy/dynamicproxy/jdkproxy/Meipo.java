package com.hzq.netty.gupao.proxy.dynamicproxy.jdkproxy;

import com.hzq.netty.gupao.proxy.dynamicproxy.Person;
import org.aopalliance.intercept.Invocation;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.InvokeHandler;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-01-07
 */
public class Meipo implements InvocationHandler {
    private Person person;

    public Object getInstance(Person person){
        this.person = person;

        Class<?> aClass = person.getClass();

        return Proxy.newProxyInstance(aClass.getClassLoader(),aClass.getInterfaces(),this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        this.before();
        Object invoke = method.invoke(this.person, args);
        this.after();
        return invoke;
    }

    private void before(){
        System.out.println("已确认要求");
    }

    private void after(){
        System.out.println("可以？谈恋爱吧");
    }

}
