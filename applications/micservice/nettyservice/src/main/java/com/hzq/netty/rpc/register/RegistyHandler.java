package com.hzq.netty.rpc.register;

import com.hzq.netty.rpc.protocol.InvokeProtocol;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class RegistyHandler extends ChannelInboundHandlerAdapter {

    private List<String> classNames = new ArrayList<>();

    private Map<String,Object> registryMap = new ConcurrentHashMap<>();

    // 1.获取所有provider类  保存到容器里（分布式情况读取配置文件）
    public RegistyHandler(){
        scannerClass("com.hzq.netty.rpc.provider");

        doRegistry();
    }

    private void doRegistry() {
        //
        if(CollectionUtils.isEmpty(this.classNames)) {
            return;
        }

        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                Class<?> i = clazz.getInterfaces()[0];
                String name = i.getName();
                //应该是网络地址  调用时解析调用  简化用对象调用
                registryMap.put(name,clazz.newInstance());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //包扫描  扫描本地class （配置文件）
    private void scannerClass(String pkg) {
        //
        URL resource = this.getClass().getClassLoader().getResource(pkg.replaceAll("\\.", "/"));
        File file = new File(resource.getFile());

        for (File listFile : file.listFiles()) {
            if(listFile.isDirectory()) {
                scannerClass(pkg+"."+listFile);
            }else{
                classNames.add(pkg+"."+listFile.getName().replaceAll(".class",""));
            }
        }

    }

    //客户端链接的回调

    // 2.链接后获取InvokeProtocol对象
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        InvokeProtocol invokeProtocol = (InvokeProtocol) msg;

        Object o = new Object();
        if(registryMap.containsKey(invokeProtocol.getClassName())){
            // 3.获取provider 远程调用  返回结果
            Object service = registryMap.get(invokeProtocol.getClassName());

            Method method = service.getClass().getMethod(invokeProtocol.getMethod(), invokeProtocol.getParas());
            o = method.invoke(service, invokeProtocol.getValues());
        }

        ctx.write(o);
        ctx.flush();
        ctx.close();

    }

    //链接异常回调
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {

    }
}
