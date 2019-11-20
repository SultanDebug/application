package com.hzq.democlient;

import com.hzq.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@EnableApplication
public class DemoclientApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoclientApplication.class, args);
    }
}
