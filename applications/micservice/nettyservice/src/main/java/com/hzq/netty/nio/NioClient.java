package com.hzq.netty.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioClient {

    public static void main(String[] args) {
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
    }

}
