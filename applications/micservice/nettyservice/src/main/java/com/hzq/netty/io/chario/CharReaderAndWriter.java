package com.hzq.netty.io.chario;

import java.io.*;
import java.net.URISyntaxException;
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

    public static void main(String[] args) {
        CharReaderAndWriter charReaderAndWriter = new CharReaderAndWriter();
        charReaderAndWriter.bufferReader();
    }

}
