package com.hzq.demoservice.test.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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
