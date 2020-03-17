package com.hzq.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
//@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.hzq.*")
//@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients(basePackages = ("com.hzq.*"))
public class AllProviderApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(AllProviderApp.class,args);
    }
}
