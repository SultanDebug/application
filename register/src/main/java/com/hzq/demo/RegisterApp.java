package com.hzq.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Hello world!
 *
 */
@EnableEurekaServer
@SpringBootApplication
public class RegisterApp
{
    public static void main( String[] args )
    {

        new SpringApplicationBuilder(RegisterApp.class).web(true).run(args);
    }
}
