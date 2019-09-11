package com.hzq.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Huangzq
 * @title: PersonProperties
 * @projectName applications
 * @date 2019/9/11 15:10
 */
@ConfigurationProperties(prefix = "spring.person")
@Data
public class PersonProperties {
    private String name;
    private Integer age;
    private String sex;
}
