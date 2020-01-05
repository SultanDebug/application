package com.hzq.netty.bio.tomcat.http;

import java.io.InputStream;

public class MyRequest {
    private String url;
    private String method;
    public MyRequest(InputStream in){
        try {
            String content = "";
            byte[] buffer = new byte[1024];
            int len = 0;

            if((len = in.read(buffer)) > 0){
                content = new String(buffer);
            }

            String line = content.split("\\n")[0];
            String [] arr = line.split(" ");

            this.url = arr[0];
            this.method = arr[1].split("\\?")[0];

            System.out.println("收到request："+content);

        }catch (Exception e){
            e.printStackTrace();
        }


    }

    public String getUrl() {
        return url;
    }

    public String getMethod() {
        return method;
    }
}
