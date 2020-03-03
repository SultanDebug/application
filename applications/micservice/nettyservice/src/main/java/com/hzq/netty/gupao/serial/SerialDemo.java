package com.hzq.netty.gupao.serial;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-02-22
 */
public class SerialDemo {
    public static void main(String[] args) {
//        ISerializer iSerializer = new JavaSerializer();
        ISerializer iSerializer = new JavaFileSerializer();

        User user = new User();
        user.setAge(22);
        user.setName("sultan");

        byte[] bytes = iSerializer.serialObj(user);

        System.out.println("序列化user:"+new String(bytes));

        User o = iSerializer.deSerialObj(bytes);

        System.out.println("反序列化user:"+o.toString());

    }
}
