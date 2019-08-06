package com.hzq.democlient.controller;

import com.hzq.demoservice.DemoServiceInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huangzq
 * @title: DemoClientController
 * @projectName applications
 * @date 2019/8/6 15:59
 */
@RestController
@Api(tags = "feign和中间件测试接口")
@Slf4j
public class DemoClientController {
    @Value("${test.val}")
    private String val;

    @Autowired
    private DemoServiceInterface demoServiceInterface;

    @ApiOperation(value = "client端")
    @GetMapping("/democlient/test/{name}")
    public String clientTest(@PathVariable("name") String name){
        return "clienttest para :"+name +". demoservice para : "+ demoServiceInterface.getTest(name) + ",当前配置文件数据：" + val;
    }
}
