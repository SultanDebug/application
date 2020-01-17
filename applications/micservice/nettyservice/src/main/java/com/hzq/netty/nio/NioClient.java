package com.hzq.netty.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.Set;

public class NioClient {

    private Selector selector;

    public NioClient(int port) {
        SocketChannel socketChannel = null;
        try {
            socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);

            selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress(InetAddress.getLocalHost(),port));
            
            while(true){
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                
                selectionKeys.forEach(selectionKey -> proccess(selectionKey));
                selectionKeys.clear();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    private void proccess(SelectionKey selectionKey) {
        try{
            //
            if(selectionKey.isConnectable()){
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                if(socketChannel.isConnectionPending()){
                    socketChannel.finishConnect();
                    System.out.println("连接成功");

                    new Thread(() -> {
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        while (true){
                            try {
                                byteBuffer.clear();
                                Scanner scanner = new Scanner(System.in);
                                String str = scanner.nextLine();

                                byteBuffer.put(str.getBytes());

                                byteBuffer.flip();
                                socketChannel.write(byteBuffer);

                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }).start();
                }
                socketChannel.register(selector,SelectionKey.OP_READ);
            }else if(selectionKey.isReadable()){
                SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                byteBuffer.clear();
                int len;
                while ((len = socketChannel.read(byteBuffer)) > 0 ){
                    String result = new String(byteBuffer.array());
                    System.out.println("收到服务器消息："+result);
                }

            }
        }catch (Exception e ){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new NioClient(8888);
    }

    /*public void fileRead(){
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("C:\\Users\\HZQ\\Desktop\\test.txt","rw");
            FileChannel fileChannel = randomAccessFile.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(64);

            int len = -1;
            while((len = fileChannel.read(byteBuffer)) != -1){
                System.out.println("读取文件:"+ len);

                byteBuffer.flip();

                byte[] tmp = new byte[1024];
                int i = 0;
                while(byteBuffer.hasRemaining()){
                    tmp[i] = byteBuffer.get();
                    i++;

                }
                System.out.println("读取缓存："+new String(tmp));


                byteBuffer.clear();
            }

            randomAccessFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

}
