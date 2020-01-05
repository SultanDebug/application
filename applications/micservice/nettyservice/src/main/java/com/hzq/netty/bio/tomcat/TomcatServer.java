package com.hzq.netty.bio.tomcat;

import com.hzq.netty.bio.tomcat.http.MyRequest;
import com.hzq.netty.bio.tomcat.http.MyResponse;
import com.hzq.netty.bio.tomcat.http.MyServlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TomcatServer {
    private int port = 8888;
    private ServerSocket serverSocket;
    private Map<String, MyServlet> map = new HashMap<>();
    private Properties webxml = new Properties();

    private void init(){
        try {
            String path = this.getClass().getResource("/").getPath();
            FileInputStream fis = new FileInputStream(path+"web.properties");
            webxml.load(fis);

            for (Object o : webxml.keySet()) {
                String key = o.toString();
                if(key.endsWith(".url")){
                    String url = webxml.getProperty(key);
                    String pre = key.replaceAll("\\.url","");

                    String className = webxml.getProperty(pre+".className");

                    MyServlet myServlet = (MyServlet) Class.forName(className).newInstance();

                    map.put(url,myServlet);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void start(){
        init();

        try {
            serverSocket = new ServerSocket(this.port);

            System.out.println("服务器已启动："+port);

            while(true){
                Socket socket = serverSocket.accept();
                proccess(socket);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void proccess(Socket socket) {
        try {
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            MyRequest myRequest = new MyRequest(inputStream);
            MyResponse myResponse = new MyResponse(outputStream);

            String url = myRequest.getMethod();

            if(map.containsKey(url)){
                map.get(url).service(myRequest,myResponse);
            }else{
                myResponse.write("404 - Not Found");
            }

            outputStream.flush();
            outputStream.close();

            inputStream.close();
            socket.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new TomcatServer().start();
    }
}
