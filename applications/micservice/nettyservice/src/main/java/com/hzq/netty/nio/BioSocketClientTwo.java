package com.hzq.netty.nio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class BioSocketClientTwo {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket(InetAddress.getLocalHost(),8888);
        Thread thread1 = new Thread(() -> {
            while (true) {
                try {
//                  Socket socket = new Socket("localhost",8888);
                    OutputStream outputStream = socket.getOutputStream();


//                  String s = UUID.randomUUID().toString();
                    Scanner scanner = new Scanner(System.in);
                    String s = scanner.nextLine();
                    outputStream.write(s.getBytes());

                    System.out.println("发送消息：" + s);

                    outputStream.close();
//                socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                try {

                    InputStream inputStream = socket.getInputStream();

                    byte[] bytes = new byte[1024];
                    int a = inputStream.read(bytes);

                    if (a > 0) {
                        String s = new String(bytes);
                        System.out.println("接收消息：" + s);
//                        Thread.sleep(1000);
                    }

//                    inputStream.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();


    }
}
