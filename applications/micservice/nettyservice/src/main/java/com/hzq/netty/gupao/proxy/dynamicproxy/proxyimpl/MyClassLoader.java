package com.hzq.netty.gupao.proxy.dynamicproxy.proxyimpl;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-01-07
 */
public class MyClassLoader extends ClassLoader {

    private File file;

    public MyClassLoader() {
        String path = MyClassLoader.class.getResource("").getPath();
        this.file = new File(path);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String className = MyClassLoader.class.getPackage().getName()+"."+name;

        if(file != null){
            File file1 = new File(file,name.replaceAll("\\.","/")+".class");
            if(file1.exists()){
                FileInputStream in ;
                ByteArrayOutputStream out ;

                try {
                    in = new FileInputStream(file1);
                    out = new ByteArrayOutputStream();

                    byte[] bytes = new byte[1024];
                    int len;
                    while ((len = in.read(bytes)) != -1){
                        out.write(bytes,0,len);
                    }
                    return defineClass(className,out.toByteArray(),0,out.size());

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        return null;
    }
}
