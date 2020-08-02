package com.hzq.kafkaservice;

import com.hzq.common.annotation.EnableApplication;
import org.springframework.boot.SpringApplication;

/**
 * kafka测试服务
 * @author Huangzq
 * @title: ProviderService
 * @projectName applications
 * @date 2019/8/31 16:14
 */
@EnableApplication
public class KafkaserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaserviceApplication.class, args);
    }

}
