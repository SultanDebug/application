package com.hzq.netty.aopcondition.autoconfiguration.importpkg;

import com.hzq.netty.aopcondition.autoconfiguration.config.BeanConfig;
import com.hzq.netty.aopcondition.autoconfiguration.config.Two;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-04-07
 */
//@Component
//@Import(BeanConfig.class)
public class ImportConfig {

    @Bean
    Two getTwo(){
        return new Two();
    }
}
