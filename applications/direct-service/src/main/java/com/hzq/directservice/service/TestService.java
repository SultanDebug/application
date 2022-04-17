package com.hzq.directservice.service;

import com.hzq.common.interceptor.feign.module.MyRpcReference;
import com.hzq.common.interceptor.feign.module.RpcProxyDemoInterface;
import org.springframework.stereotype.Service;

/**
 * @Desc TODO
 * @Author Huang
 * @Date 2022/4/17 21:07
 **/
@Service
public class TestService {
    @MyRpcReference(host = "192.168.1.1", port = "8888")
    private RpcProxyDemoInterface rpcProxyDemoInterface;

    public String rpcModule() throws InterruptedException, NoSuchFieldException, IllegalAccessException {

        /*RpcProxy proxy = new RpcProxy("192.168.2.2","9999");
        Object o = Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{RpcProxyDemoInterface.class}, proxy);
        Field rpcProxyDemoInterface = this.getClass().getDeclaredField("rpcProxyDemoInterface");
        rpcProxyDemoInterface.setAccessible(true);
        rpcProxyDemoInterface.set(this,o);*/

        String para = rpcProxyDemoInterface.func1("para");
        String para1 = rpcProxyDemoInterface.func2("para1", 2);
        return para + "-----" + para1;
    }
}
