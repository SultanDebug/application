package com.hzq.demoservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
/*@EnableDiscoveryClient*/
@EnableFeignClients(basePackages = ("com.hzq.*"))
public class DemoserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoserviceApplication.class, args);
    }



}
