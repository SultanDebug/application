package com.hzq.feign;

import com.hzq.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;

/**
 * 数据库测试
 * 远程服务提供者
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
