package com.hzq.netty.gupao.serial;

import java.io.*;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-22
 */
public class JavaFileSerializer implements ISerializer {
    @Override
    public <T> byte[] serialObj(T obj) {

        try {
            FileOutputStream fileOutputStream = new FileOutputStream("serialfile.txt");

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(obj);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new byte[0];
    }

    @Override
    public <T> T deSerialObj(byte[] bytes) {

        try {
            FileInputStream fileInputStream = new FileInputStream("serialfile.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            T o = (T) objectInputStream.readObject();
            return o;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
