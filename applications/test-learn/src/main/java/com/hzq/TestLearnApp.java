package com.hzq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = "com.hzq")
public class TestLearnApp
{
    public static void main( String[] args )
    {
        SpringApplication.run(TestLearnApp.class,args);
    }
}
