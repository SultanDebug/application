package com.hzq.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
    /**
     * 轮询器 selector 叫号
     * 缓冲区 buffer   等号
     */

    private Selector selector;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    private int port = 8888;

    public NioServer(int port) throws IOException {
        //初始化selector 叫号系统
        this.port = port;

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(this.port));

        //nio兼容bio  nio默认采用阻塞式
        serverSocketChannel.configureBlocking(false);


        selector = Selector.open();

        //准备接收消息
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void listen() throws IOException {
        System.out.println("监听端口："+port);
        //轮询 非阻塞
        while (true){
            int select = selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();

            //同步体现  只处理一个
            while (iterator.hasNext()){
                SelectionKey next = iterator.next();

                iterator.remove();

                proccess(next);
            }


        }
    }

    public void proccess(SelectionKey selectionKey) throws IOException {
        if(selectionKey.isAcceptable()) {
            ServerSocketChannel channel = (ServerSocketChannel) selectionKey.channel();
            SocketChannel accept = channel.accept();

            accept.configureBlocking(false);
            accept.register(selector,SelectionKey.OP_READ);
        }else if(selectionKey.isReadable()){
            SocketChannel channel = (SocketChannel) selectionKey.channel();

            int len = channel.read(byteBuffer);
            if(len > 0){
                byteBuffer.flip();
                String s = new String(byteBuffer.array(),0,len);

                //改成可写
                selectionKey = channel.register(selector,SelectionKey.OP_WRITE);
                selectionKey.attach(s);
                System.out.println("读取消息："+s);

            }
        }else if(selectionKey.isWritable()){

            /*try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/

            SocketChannel channel = (SocketChannel) selectionKey.channel();

            String s = (String) selectionKey.attachment();

            channel.write(ByteBuffer.wrap(("输出："+s).getBytes()));

            channel.close();
        }
    }

    public static void main(String[] args) throws IOException {
        new NioServer(8888).listen();
    }

}
