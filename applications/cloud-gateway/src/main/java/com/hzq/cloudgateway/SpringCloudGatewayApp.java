package com.hzq.cloudgateway;

import com.hzq.cloudgateway.filter.SecurityFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@EnableFeignClients(basePackages = "com.hzq")
public class SpringCloudGatewayApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(SpringCloudGatewayApp.class,args);
    }

    @Bean
    public RouteLocator getFilter1(RouteLocatorBuilder builder){

        return builder.routes()
                .route(o->o.path("/client/**")
//                .filters(f->f.filter(new SecurityFilter()))
                .uri("http://localhost:20001")
                .order(0)
                .id("client")
        ).build();

        /*.route(
                o->o.path("/ws/**")
                .filters(f->f.filter(new SecurityFilter()))
                .uri("http://localhost:20001")
                .order(0)
                .id("client")
         )*/
    }

}
