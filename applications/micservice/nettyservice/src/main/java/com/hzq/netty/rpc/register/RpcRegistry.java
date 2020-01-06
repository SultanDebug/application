package com.hzq.netty.rpc.register;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.ScheduledFuture;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class RpcRegistry {
    private int port = 8888;

    RpcRegistry(int port){
        this.port = port;
    }

    public void start() throws InterruptedException {
        //BIO ServerSocket //Nio ServerSocketChannel
        //selector boos主线程  worker工作线程

        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        //初始化server
        serverBootstrap.group(boss,worker)
                //轮询所有keys 主线程处理类
                .channel(NioServerSocketChannel.class)
                //子线程处理类
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        //业务归宗到一个队列
                        //无锁化串行队列
                        ChannelPipeline channelPipeline = socketChannel.pipeline();

                        //自定义协议编码
                        channelPipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                        //自定义协议解码
                        channelPipeline.addLast(new LengthFieldPrepender(4));

                        //实参处理
                        channelPipeline.addLast("encoder",new ObjectEncoder());
                        channelPipeline.addLast("decoder",new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.cacheDisabled(null)));

                        //以上完成数据解析
                        //以下完成自定义逻辑
                        //注册服务  对外提供服务的名字  服务位置登记
                        channelPipeline.addLast(new RegistyHandler());

                    }
                })
                //主线程配置  线程数
                .option(ChannelOption.SO_BACKLOG,128)
                //子线程配置  长连接
                .childOption(ChannelOption.SO_KEEPALIVE,true);

        //服务启动  相当于死循环轮询
        ChannelFuture sync = serverBootstrap.bind(this.port).sync();
        System.out.println("RPC regist start : "+ this.port);
        sync.channel().closeFuture().sync();

    }

    public static void main(String[] args) {
        try {
            new RpcRegistry(8888).start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
