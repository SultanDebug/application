package com.hzq.discovery;

import com.hzq.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


/**
 * Hello world!
 *
 */
//@EnableAutoConfiguration
//@EnableDiscoveryClient

@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.hzq.*")
@EnableFeignClients

//@EnableFeignClients(basePackages = ("com.hzq.*"))  ERROR:interface bean 扫描重复
//    @EnableApplication
public class DiscoveryAllApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(DiscoveryAllApp.class,args);
    }

}
