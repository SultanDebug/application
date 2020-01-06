package com.hzq.netty.rpc.comsumer.proxy;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class RpcProxyHandler extends ChannelInboundHandlerAdapter {
    private Object resopnse;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        resopnse = msg;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("错误：" +cause.getMessage());
    }

    public Object getResponse() {
        return resopnse;
    }
}
