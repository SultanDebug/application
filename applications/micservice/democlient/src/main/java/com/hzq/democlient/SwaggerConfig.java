package com.hzq.democlient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Huangzq
 * @date 2019-01-22
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {

        //添加header参数
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name("user").description("user thread local")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build(); //header中的ticket参数非必填，传空也可以
        pars.add(ticketPar.build());    //根据每个方法名也知道当前方法在设置什么参数

        return new Docket(DocumentationType.SWAGGER_2) //
                .apiInfo(apiInfo()) //
                .select() //
                .apis(RequestHandlerSelectors.basePackage("com.hzq.democlient.controller")) // 注意修改此处的包名
                .paths(PathSelectors.any())//
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("黄大狗", "", "hzqsultan9527@gmail.com");
        return new ApiInfoBuilder().title("democlient-客户端测试") // 任意，请稍微规范点
                .description("微服务测试接口:http://sultanlocal.free.idcfengye.com/swagger-ui.html") // 任意，请稍微规范点
                .contact(contact) // 无所谓（这里是作者的别称）
                .version("1.0") //
                .build();
    }
}
