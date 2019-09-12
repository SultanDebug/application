package com.hzq.democlient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
/*@EnableDiscoveryClient*/
@EnableFeignClients(basePackages = ("com.hzq.*"))
public class DemoclientApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoclientApplication.class, args);
    }
}
