package com.hzq.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * zuul网关服务
 *
 */
@EnableEurekaClient
@SpringBootApplication
@EnableZuulProxy
public class GateWayApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(GateWayApp.class,args);
    }

    /*@Bean
    PostFilter postFilter(Tracer tracer) {
        return new PostFilter(tracer);
    }*/


}
