package com.hzq.common.load;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.hzq.common.load", "com.hzq.common.utils"})
public class LoadApp {
    public static void main(String[] args) {
        SpringApplication.run(LoadApp.class, args);
    }
}
