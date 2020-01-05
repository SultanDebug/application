package com.hzq.netty.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class BioSocketServer {
    private ServerSocket serverSocket;
    public BioSocketServer(int port){
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("服务已启动："+port);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void listen() throws IOException {
        while (true){
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();

            while (true){
                byte[] bytes = new byte[3];

                int read = inputStream.read(bytes);

                if(read > 0){
                    String s = new String(bytes,0,read);
                    System.out.println("收到消息："+s);
                }else{
                    break;
                }
            }

        }
    }

    public static void main(String[] args) throws IOException {
        new BioSocketServer(8888).listen();
    }

}
