package com.hzq.netty.bio.tomcatnetty;

import com.hzq.netty.bio.tomcatnetty.http.MyRequest;
import com.hzq.netty.bio.tomcatnetty.http.MyResponse;
import com.hzq.netty.bio.tomcatnetty.http.MyServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TomcatServer {
    private int port = 8888;
    private Map<String, MyServlet> map = new HashMap<>();
    private Properties webxml = new Properties();

    private void init(){
        try {
            String path = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(path+"web.properties");
            webxml.load(fis);

            for (Object o : webxml.keySet()) {
                String key = o.toString();
                if(key.endsWith(".url")){
                    String url = webxml.getProperty(key);
                    String pre = key.replaceAll("\\.url","");

                    String className = webxml.getProperty(pre+".className");

                    MyServlet myServlet = (MyServlet) Class.forName(className).newInstance();

                    map.put(url,myServlet);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start(){
        init();
        //netty封装nio boos线程  work线程
        EventLoopGroup boss = new NioEventLoopGroup();

        EventLoopGroup work = new NioEventLoopGroup();

        try {

            //netty服务  nio：ServerSocketChannel
            ServerBootstrap serverBootstrap = new ServerBootstrap();

            //责任链模式  链路是编程
            serverBootstrap.group(boss,work)
                    //主线程处理类
                    .channel(NioServerSocketChannel.class)
                    //子线程处理类
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        //客户端初始化处理
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new HttpResponseEncoder());
                            socketChannel.pipeline().addLast(new HttpRequestDecoder());
                            socketChannel.pipeline().addLast(new TomcatHandler());
                        }
                    })
                    //主线程配置  最大线程数128
                    .option(ChannelOption.SO_BACKLOG,128)
                    //子线程配置  保存长连接
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            ChannelFuture sync = serverBootstrap.bind(port).sync();

            System.out.println("服务器已启动："+port);

            sync.channel().closeFuture().sync();


            /*serverSocket = new ServerSocket(this.port);

            System.out.println("服务器已启动："+port);

            while(true){
                Socket socket = serverSocket.accept();
                proccess(socket);
            }*/
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public class TomcatHandler extends ChannelInboundHandlerAdapter{
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

            if(msg instanceof HttpRequest){
                HttpRequest request = (HttpRequest) msg;

                MyRequest myRequest = new MyRequest(ctx,request);

                MyResponse myResponse = new MyResponse(ctx,request);

                String url = myRequest.getUrl();

                if(map.containsKey(url)){
                    map.get(url).service(myRequest,myResponse);
                }else{
                    myResponse.write("404 - Not Found");
                }

            }
        }
    }

    /*private void proccess(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            MyRequest myRequest = new MyRequest(inputStream);
            MyResponse myResponse = new MyResponse(outputStream);

            String url = myRequest.getMethod();

            if(map.containsKey(url)){
                map.get(url).service(myRequest,myResponse);
            }else{
                myResponse.write("404 - Not Found");
            }

            outputStream.flush();
            outputStream.close();

            inputStream.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }*/

    public static void main(String[] args) {
        new TomcatServer().start();
    }
}
