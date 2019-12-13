package com.hzq.zkservice;

import com.hzq.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 *
 */
@EnableApplication
public class ZkApplication
{
    public static void main( String[] args )
    {
        SpringApplication.run(ZkApplication.class,args);
    }
}
