package com.hzq.demoservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@SpringBootApplication
public class DemoserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoserviceApplication.class, args);
    }


    @Value("${server.port}")
    String port;

    @GetMapping("/demo/{hostName}")
    public String getPort(@PathVariable String hostName){
        return "测试数据：http://"+hostName+":"+port;
    }


}
