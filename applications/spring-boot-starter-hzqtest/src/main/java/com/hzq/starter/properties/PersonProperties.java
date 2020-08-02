package com.hzq.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

/**
 * starter参数获取
 * @author Huangzq
 * @title: PersonProperties
 * @projectName applications
 * @date 2019/9/11 15:10
 */
@ConfigurationProperties(prefix = "spring.person")
@RefreshScope
@Data
public class PersonProperties {
    private String name;
    private Integer age;
    private String sex;
}
