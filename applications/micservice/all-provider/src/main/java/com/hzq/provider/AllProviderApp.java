package com.hzq.provider;

import com.hzq.common.annotation.EnableApplication;
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
@EnableFeignClients
//@EnableFeignClients(basePackages = ("com.hzq.*"))  ERROR:interface bean 扫描重复
//    @EnableApplication
public class AllProviderApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(AllProviderApp.class,args);
    }
}
