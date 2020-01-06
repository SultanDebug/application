package com.hzq.netty.rpc.provider;

import com.hzq.netty.rpc.api.RpcService;

public class RpcServiceImpl implements RpcService {
    @Override
    public int add(int a, int b) {
        System.out.println("进入加方法："+a+"/"+b);
        return a+b;
    }

    @Override
    public int sub(int a, int b) {
        return a-b;
    }

    @Override
    public int mult(int a, int b) {
        return a*b;
    }

    @Override
    public int dev(int a, int b) {
        return a/b;
    }
}
