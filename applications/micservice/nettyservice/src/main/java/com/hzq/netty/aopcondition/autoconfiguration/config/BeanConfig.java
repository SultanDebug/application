package com.hzq.netty.aopcondition.autoconfiguration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-04-07
 */
//@Configuration
public class BeanConfig {

    @Bean
    One getOne(){
        return new One();
    }
}
