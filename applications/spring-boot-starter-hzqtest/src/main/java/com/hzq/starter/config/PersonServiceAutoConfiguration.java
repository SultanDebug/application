package com.hzq.starter.config;

import com.hzq.starter.properties.PersonProperties;
import com.hzq.starter.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * starter插拔式 条件配置
 * @author Huangzq
 * @title: PersonServiceAutoConfiguration
 * @projectName applications
 * @date 2019/9/11 15:18
 */
@Configuration
@EnableConfigurationProperties(PersonProperties.class)
@ConditionalOnClass(PersonService.class)
@ConditionalOnProperty(prefix = "spring.person" ,value = "enable",matchIfMissing = true)
public class PersonServiceAutoConfiguration {

    @Autowired
    private PersonProperties personProperties;

    @Bean
    @ConditionalOnMissingBean(PersonService.class)
    public PersonService personService(){
        return new PersonService(personProperties);
    }

}
