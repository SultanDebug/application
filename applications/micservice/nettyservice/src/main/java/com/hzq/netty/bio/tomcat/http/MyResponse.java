package com.hzq.netty.bio.tomcat.http;

import java.io.IOException;
import java.io.OutputStream;

public class MyResponse {
    private OutputStream os;
    public MyResponse(OutputStream os) {
        this.os=os;
    }

    public void write(String para) {
        StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(para);

        try {
            os.write(sb.toString().getBytes());
            /*os.flush();
            os.close();*/
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
