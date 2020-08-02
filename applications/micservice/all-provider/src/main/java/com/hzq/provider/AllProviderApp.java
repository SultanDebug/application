package com.hzq.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 全注册发现中间件提供者测试
 *
 */
//@EnableAutoConfiguration
@SpringBootApplication(scanBasePackages = "com.hzq.*")
//@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
//@EnableFeignClients(basePackages = ("com.hzq.*"))  【ERROR:interface bean 扫描重复】
//    @EnableApplication
public class AllProviderApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(AllProviderApp.class,args);
    }
}
