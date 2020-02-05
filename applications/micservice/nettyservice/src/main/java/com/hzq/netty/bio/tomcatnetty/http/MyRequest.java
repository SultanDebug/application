package com.hzq.netty.bio.tomcatnetty.http;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MyRequest {
    private ChannelHandlerContext cxt;
    private HttpRequest request;
    public MyRequest(ChannelHandlerContext cxt,HttpRequest request){
        this.cxt = cxt;
        this.request = request;
        /*try {
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
        }*/


    }

    public String getUrl() {
        return request.uri();
    }

    public String getMethod() {
        return request.method().name();
    }

    public Map<String , List<String>> getParas(){
        QueryStringDecoder queryStringDecoder = new QueryStringDecoder(request.uri());
        return queryStringDecoder.parameters();
    }

    public String getPara(String name){
        Map<String , List<String>> map = this.getParas();
        List<String> list = map.get(name);
        if(!CollectionUtils.isEmpty(list)){
            return list.get(0);
        }
        return null;
    }
}
