package com.hzq.demoservice.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Huangzq
 * @date 2019-04-15
 */
public class HttpUtil {

    public static void test(String[] args) throws Exception {
        String urlStr = "http://cc.porsche.com/icc_euro/ccCall.do?userID=CN&lang=cn&PARAM=parameter_internet_cn&hookURL=https://www.porsche.com/china/zh/modelstart/all/default.ashx&webcode=PLQ7X368";

        //读取目的网页URL地址，获取网页源码
        URL url = new URL(urlStr);
        HttpURLConnection httpUrl = (HttpURLConnection)url.openConnection();
        InputStream is = httpUrl.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is,"utf-8"));
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            //这里是对链接进行处理
            //line = line.replaceAll("</?a[^>]*>", "");
            //这里是对样式进行处理
            //line = line.replaceAll("<(\\w+)[^>]*>", "<$1>");
            sb.append(line);
        }
        is.close();
        br.close();
        String result = sb.toString().trim();

        System.out.println(result);

    }

}
