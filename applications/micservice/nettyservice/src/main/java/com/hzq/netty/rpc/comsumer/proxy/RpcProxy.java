package com.hzq.netty.rpc.comsumer.proxy;

import com.hzq.netty.rpc.protocol.InvokeProtocol;
import com.hzq.netty.rpc.register.RegistyHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.omg.CORBA.SystemException;
import org.omg.CORBA.portable.InputStream;
import org.omg.CORBA.portable.InvokeHandler;
import org.omg.CORBA.portable.OutputStream;
import org.omg.CORBA.portable.ResponseHandler;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class RpcProxy {
    public static<T> T create(Class<?> clazz){
        MethodProxy methodProxy = new MethodProxy(clazz);
        T t = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class[]{clazz}, methodProxy);
        return t;
    }

    //将本地调用变成网络调用
    public static class MethodProxy implements InvocationHandler {

        private Class<?> clazz;

        public MethodProxy(Class<?> clazz) {
            this.clazz = clazz;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if(Object.class.equals(method.getDeclaringClass())){
                return method.invoke(this,args);
            }else {
                return rpcInvoke(proxy,method,args);
            }
        }

        private Object rpcInvoke(Object proxy, Method method, Object[] args) throws InterruptedException {
            InvokeProtocol invokeProtocol = new InvokeProtocol();
            invokeProtocol.setClassName(method.getDeclaringClass().getName());
            invokeProtocol.setMethod(method.getName());
            invokeProtocol.setParas(method.getParameterTypes());
            invokeProtocol.setValues(args);

            EventLoopGroup workGroup = new NioEventLoopGroup();
            Bootstrap client = new Bootstrap();

            final RpcProxyHandler rpcProxyHandler = new RpcProxyHandler();

            client.group(workGroup)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
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
                            channelPipeline.addLast(rpcProxyHandler);
                        }
                    });

            ChannelFuture localhost = client.connect("localhost", 8888).sync();
            localhost.channel().writeAndFlush(invokeProtocol).sync();
            localhost.channel().close().sync();

            workGroup.shutdownGracefully();

            return rpcProxyHandler.getResponse();
        }
    }
}
