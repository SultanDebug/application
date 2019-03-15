package com.hzq.registerserv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class RegisterservApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterservApplication.class, args);
    }

}
