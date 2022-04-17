package com.hzq.common.interceptor.feign.module;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Desc TODO
 * @Author Huang
 * @Date 2022/4/17 14:19
 **/
@Slf4j
public class RpcProxy implements InvocationHandler {

    private String host;
    private String port;

    public RpcProxy(String host, String port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return RpcCall.call(host,port,method,args);
    }
}
