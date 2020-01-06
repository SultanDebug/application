package com.hzq.netty.rpc.comsumer;

import com.hzq.netty.rpc.api.RpcService;
import com.hzq.netty.rpc.comsumer.proxy.RpcProxy;
import com.hzq.netty.rpc.provider.RpcServiceImpl;

public class RpcComsumer {
    public static void main(String[] args) {
        RpcService rpcService = RpcProxy.create(RpcService.class);

        System.out.println("1+1 = "+rpcService.add(1,1));
    }
}
