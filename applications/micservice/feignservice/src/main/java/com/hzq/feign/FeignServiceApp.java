package com.hzq.feign;

import com.hzq.common.annotation.EnableApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;

/**
 * Hello world!
 *
 */
@EnableApplication
public class FeignServiceApp
{
    public static void main( String[] args )
    {

        SpringApplication.run(FeignServiceApp.class, args);
    }
}
