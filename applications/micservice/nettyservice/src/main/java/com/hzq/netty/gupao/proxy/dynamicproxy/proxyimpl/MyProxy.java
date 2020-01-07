package com.hzq.netty.gupao.proxy.dynamicproxy.proxyimpl;

import com.hzq.netty.gupao.proxy.dynamicproxy.Person;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-01-07
 */
public class MyProxy {
    public static Object newProxyInstance(MyClassLoader loader,
                                          Class<?>[] interfaces,
                                          MyInvocationHandler h){

        //生成class文件  实例化
        try {
            String src = genericSrc(loader,interfaces,h);

            File file = new File(MyProxy.class.getResource("").getPath()+"$Proxy0.java");
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(src);
            fileWriter.flush();
            fileWriter.close();

            JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager standardFileManager = javaCompiler.getStandardFileManager(null, null, null);
            Iterable javaFileObjects = standardFileManager.getJavaFileObjects(file);

            JavaCompiler.CompilationTask task = javaCompiler.getTask(null, standardFileManager, null, null, Arrays.asList(MyMeipo.class.getCanonicalName()), null);

            task.call();
            standardFileManager.close();

            Class proxyClass = loader.findClass("$Proxy0");
            Constructor constructor = proxyClass.getConstructor(MyInvocationHandler.class);

            return constructor.newInstance(h);

        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }

    private static String genericSrc(MyClassLoader loader,
                                     Class<?>[] interfaces,
                                     MyInvocationHandler h) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.hzq.netty.gupao.proxy.dynamicproxy.proxyimpl; \r\n");
        sb.append("import com.hzq.netty.gupao.proxy.dynamicproxy.Person; \r\n");
        sb.append("import java.lang.reflect.*; \r\n");
        sb.append("public class $Proxy0 implements "+interfaces[0].getSimpleName() +"{ \r\n");
        sb.append("MyInvocationHandler h;\r\n");
        sb.append("public $Proxy0(MyInvocationHandler h){\r\n");
        sb.append("this.h = h;\r\n");
        sb.append("}\r\n");

        for (Method method : interfaces[0].getMethods()) {
            sb.append("public "+method.getReturnType().getSimpleName()+" " + method.getName()+"(){\r\n");
            sb.append("try{\r\n");
            sb.append("Method m = "+interfaces[0].getSimpleName()+".class.getMethod(\""+method.getName()+"\",new Class[]{});\r\n");
            sb.append("this.h.invoke(this,m,null);\r\n");
            sb.append("}catch (Throwable e){\r\n" +
                    "e.printStackTrace();\r\n" +
                    "}\r\n");
            sb.append("}\r\n");
        }

        sb.append("}\r\n");

        return sb.toString();
    }

}
