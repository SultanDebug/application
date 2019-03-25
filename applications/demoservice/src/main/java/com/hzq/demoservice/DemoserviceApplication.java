package com.hzq.demoservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
/*@EnableDiscoveryClient*/
@EnableFeignClients(basePackages = ("com.hzq.*"))
public class DemoserviceApplication implements DemoServiceInterface{

    @Value("${test.val}")
    private String val;

    public static void main(String[] args) {
        SpringApplication.run(DemoserviceApplication.class, args);
    }

    @GetMapping("/demo/{name}")
    public String getTest(@PathVariable("name") String name){
        System.out.println("----------测试---------");
        return "hello "+name+",there is :"+val;
    }

}
