package com.hzq.netty.gupao.proxy.dynamicproxy.proxyimpl;

import java.lang.reflect.Method;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-01-07
 */
public interface MyInvocationHandler {
    Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
}
