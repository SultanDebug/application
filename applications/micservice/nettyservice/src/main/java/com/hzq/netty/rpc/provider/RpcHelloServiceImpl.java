package com.hzq.netty.rpc.provider;

import com.hzq.netty.rpc.api.RpcHelloService;

public class RpcHelloServiceImpl implements RpcHelloService {
    @Override
    public String say(String name) {
        return "hello:"+name;
    }
}
