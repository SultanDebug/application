package com.hzq.netty.bio.tomcatnetty.http;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.OutputStream;

public class MyResponse {
    private ChannelHandlerContext ctx;
    private HttpRequest request;
    public MyResponse(ChannelHandlerContext ctx,HttpRequest request) {
        this.ctx = ctx;
        this.request = request;
    }

    public void write(String para) {

        try {
            if(StringUtils.isBlank(para)){
                return;
            }

            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    Unpooled.wrappedBuffer(para.getBytes("UTF-8"))
            );

            response.headers().set("Content-Type","text/html");

            ctx.write(response);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //把数据刷出去
            ctx.flush();
            ctx.close();
        }

        /*StringBuilder sb = new StringBuilder();
        sb.append("HTTP/1.1 200 OK\n")
                .append("Content-Type: text/html;\n")
                .append("\r\n")
                .append(para);

        try {
            os.write(sb.toString().getBytes());
            *//*os.flush();
            os.close();*//*
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }
}
