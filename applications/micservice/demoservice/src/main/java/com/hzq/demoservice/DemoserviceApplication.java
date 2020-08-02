package com.hzq.demoservice;
import com.hzq.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;

/**
 * 远程服务提供者和中间件测试
 * @author Huangzq
 * @title: DemoserviceApplication
 * @projectName applications
 * @date 2019/7/4 18:52
 */
@EnableApplication
public class DemoserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoserviceApplication.class, args);
    }



}
