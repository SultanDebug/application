package com.hzq.demoservice;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Huangzq
 * @date 2019-01-22
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2) //
                .apiInfo(apiInfo()) //
                .select() //
                .apis(RequestHandlerSelectors.basePackage("com.hzq.demoservice.controller")) // 注意修改此处的包名
                .paths(PathSelectors.any())//
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("黄生", "", "hzqsultan9527@gmail.com");
        return new ApiInfoBuilder().title("demoservice接口") // 任意，请稍微规范点
                .description("测试地址:http://localhost:20001/swagger-ui.html") // 任意，请稍微规范点
                .contact(contact) // 无所谓（这里是作者的别称）
                .version("1.0") //
                .build();
    }
}
