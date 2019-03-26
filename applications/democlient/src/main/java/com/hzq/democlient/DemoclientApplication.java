package com.hzq.democlient;

import com.hzq.demoservice.DemoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
@RestController
/*@EnableDiscoveryClient*/
@EnableFeignClients(basePackages = ("com.hzq.*"))
public class DemoclientApplication {

    @Value("${test.val}")
    private String val;

    @Autowired
    private DemoServiceInterface demoServiceInterface;

    public static void main(String[] args) {
        SpringApplication.run(DemoclientApplication.class, args);
    }

    @GetMapping("/democlient/test/{name}")
    public String clientTest(@PathVariable("name") String name){
        return "clienttest para :"+name +". demoservice para : "+ demoServiceInterface.getTest(name) + ",当前配置文件数据：" + val;
    }

}
