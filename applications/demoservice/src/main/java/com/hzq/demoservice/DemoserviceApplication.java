package com.hzq.demoservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
public class DemoserviceApplication {

    @Value("${test.val}")
    private String val;

    public static void main(String[] args) {
        SpringApplication.run(DemoserviceApplication.class, args);
    }

    @GetMapping("/demo/{name}")
    public String getTest(@PathVariable String name){
        return "hello "+name+",there is :"+val;
    }

}
