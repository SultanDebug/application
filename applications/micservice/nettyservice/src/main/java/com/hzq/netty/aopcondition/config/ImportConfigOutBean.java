package com.hzq.netty.aopcondition.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author Huangzq
 * @title: ImportTest
 * @projectName applications
 * @date 2019/7/24 10:47
 */
@Configuration
public class ImportConfigOutBean {

//    @Bean
    public ConditionFilterTest getConditionFilter(){
        return new ConditionFilterTest();
    }

}
