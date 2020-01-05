package com.hzq.netty.bio.tomcat.servlet;

import com.hzq.netty.bio.tomcat.http.MyRequest;
import com.hzq.netty.bio.tomcat.http.MyResponse;
import com.hzq.netty.bio.tomcat.http.MyServlet;

public class SecondServlet extends MyServlet {
    public void doGet(MyRequest request, MyResponse response){
        this.doPost(request,response);
    }
    public void doPost(MyRequest request,MyResponse response){
        response.write("这是第二个sevlet返回数据");
    }
}
