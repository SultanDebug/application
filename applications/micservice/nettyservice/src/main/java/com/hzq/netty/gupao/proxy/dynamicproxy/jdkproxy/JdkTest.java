package com.hzq.netty.gupao.proxy.dynamicproxy.jdkproxy;

import com.hzq.netty.gupao.proxy.dynamicproxy.Person;
import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-01-07
 */
public class JdkTest {
    public static void main(String[] args) {
        try {
            Person person = (Person) new Meipo().getInstance(new Girl());
            person.findLove();

            /*byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0",new Class[]{Person.class});
            FileOutputStream outputStream = new FileOutputStream("E://$Proxy0.class");
            outputStream.write(bytes);
            outputStream.close();*/

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
