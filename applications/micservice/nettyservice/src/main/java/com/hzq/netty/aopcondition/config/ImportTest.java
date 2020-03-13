package com.hzq.netty.aopcondition.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Huangzq
 * @title: ImportTest
 * @projectName applications
 * @date 2019/7/24 10:47
 */
@Configuration
public class ImportTest {

//    @Bean
    public ConditionFilterTest getConditionFilter(){
        return new ConditionFilterTest();
    }

    @Bean
    @ConditionalOnBean(value = ConditionFilterTest.class)
    public ConditionTest getCondition(){
        return new ConditionTest();
    }

}
