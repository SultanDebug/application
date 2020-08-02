package com.hzq.rabbitservice;

import com.hzq.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;

/**
 * rabbitmq测试
 *
 */
@EnableApplication
public class RabbitMqApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(RabbitMqApplication.class,args);
    }
}
