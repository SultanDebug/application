package com.hzq.demo;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Hello world!
 *
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigApp
{
    public static void main( String[] args )
    {

        new SpringApplicationBuilder(ConfigApp.class).web(true).run(args);
    }
}
