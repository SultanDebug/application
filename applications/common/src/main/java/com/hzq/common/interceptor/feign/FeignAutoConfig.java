package com.hzq.common.interceptor.feign;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 拦截器注册
 * @author Huangzq
 * @title: FeignAutoConfig
 * @projectName applications
 * @date 2019/11/22 18:17
 */
@Configuration
//@EnableFeignClients(basePackages = "com.hzq.*")
@Slf4j
public class FeignAutoConfig {
    @Bean
    public FeignInterceptor init(){
        log.info("feign 拦截器初始化成功");
        return new FeignInterceptor();
    }
}
