package com.hzq.netty.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class NioProServer {
    /**
     * 轮询器 selector 叫号
     * 缓冲区 buffer   等号
     */

    private Selector selector;
    private ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

    public static CopyOnWriteArraySet<NioProServer> nioProServers = new CopyOnWriteArraySet<>();
    private SelectionKey key;
    private String id;

    private int port = 8888;

    public NioProServer(int port) throws IOException {
        //初始化selector 叫号系统
        this.port = port;

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetAddress inetAddress = InetAddress.getByName("");

        serverSocketChannel.bind(new InetSocketAddress(this.port));

        //nio兼容bio  nio默认采用阻塞式
        serverSocketChannel.configureBlocking(false);


        selector = Selector.open();

        //准备接收消息
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void onOpen(String id, SelectionKey key){

    }

    public void onMsg(String msg , SelectionKey key){}

    public void onClose(){}

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

            System.out.println("输出："+s);

            Scanner scanner = new Scanner(System.in);

            String ret = scanner.nextLine();

            channel.write(ByteBuffer.wrap(ret.getBytes()));

//            channel.register(selector,SelectionKey.OP_ACCEPT);

//            channel.close();
        }
    }

    public static void main(String[] args) throws IOException {
        new NioProServer(8888).listen();
    }

}
