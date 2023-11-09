package com.hzq.common.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.hzq"})
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.hzq"})
public class Flow {
    public static void main(String[] args) {
        SpringApplication.run(Flow.class, args);
    }
}
