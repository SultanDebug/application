package com.hzq.common.annotation;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Huangzq
 * @title: EnableApplicationConfig
 * @projectName applications
 * @date 2019/11/13 10:06
 */
@EnableEurekaClient
@SpringBootApplication(scanBasePackages = "com.hzq.*")
/*@EnableDiscoveryClient*/
@EnableFeignClients(basePackages = ("com.hzq.*"))
@MapperScan("com.hzq.*.mapper")
public class EnableApplicationConfig {
    private static final Logger log = LoggerFactory.getLogger(EnableApplicationConfig.class);

    public EnableApplicationConfig() {
        log.info("启动初始化配置......");
    }
}
