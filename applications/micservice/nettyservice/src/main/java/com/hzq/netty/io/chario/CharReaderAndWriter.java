package com.hzq.netty.io.chario;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Optional;

/**
 * @author Huangzq
 * @title: CharReaderAndWriter
 * @projectName applications
 * @date 2020/4/8 10:40
 */
public class CharReaderAndWriter {

    private String root = "iofile/test.txt";

    public void bufferReader(){
        File file = new File(Thread.currentThread().getContextClassLoader().getResource(root).getPath());

        try {
            FileReader fileReader = new FileReader(file);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            char[] chars = new char[3];

            StringBuilder stringBuilder = new StringBuilder();

            while (fileReader.read(chars) != -1){
                stringBuilder.append(chars);
//                chars.
            }

            System.out.println(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public void stringReader(){
        File file = new File(Thread.currentThread().getContextClassLoader().getResource(root).getPath());

        try {
//            FileReader fileReader = new FileReader(file);

            InputStream inputStream = new FileInputStream(file);

            /*BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            bufferedInputStream.read();*/
//            char[] chars = new char[3];

            byte[] bytes = new byte[2];

            StringBuilder stringBuilder = new StringBuilder();

            while (inputStream.read(bytes) != -1){
//                stringBuilder.append(bytes);
                System.out.println(new String(bytes));
//                chars.
            }

//            System.out.println(stringBuilder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws IOException {
//        CharReaderAndWriter charReaderAndWriter = new CharReaderAndWriter();
//        charReaderAndWriter.stringReader();
//        System.in.read();

        int intSize = Integer.SIZE;

        System.out.println("    int size: " + (intSize/8) + "Byte" );



        int shortSize = Short.SIZE;

        System.out.println("  short size: " + (shortSize/8) + "Byte" );



        int longSize = Long.SIZE;

        System.out.println("   long size: " + (longSize/8) + "Byte" );



        int byteSize = Byte.SIZE;

        System.out.println("   byte size: " + (byteSize/8) + "Byte" );



        int floatSize = Float.SIZE;

        System.out.println("  float size: " + (floatSize/8) + "Byte" );



        int doubleSize = Double.SIZE;

        System.out.println(" double size: " + (doubleSize/8) + "Byte" );

    }

}
