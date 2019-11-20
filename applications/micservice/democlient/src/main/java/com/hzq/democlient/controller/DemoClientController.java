package com.hzq.democlient.controller;

import com.hzq.common.aop.LogAop;
import com.hzq.common.utils.ApplicationContextUtils;
import com.hzq.demoservice.DemoServiceInterface;
import com.hzq.feignservice.FeignServInterface;
import com.hzq.starter.service.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.ContextLoader;

/**
 * @author Huangzq
 * @title: DemoClientController
 * @projectName applications
 * @date 2019/8/6 15:59
 */
@RestController
@Api(tags = "feign客户端和中间件测试接口")
@Slf4j
public class DemoClientController {
    @Value("${test.val}")
    private String val;

    @Autowired(required = false)
    private PersonService personService;

    /*@Autowired
    private DemoServiceInterface demoServiceInterface;*/

    @Autowired
    private FeignServInterface feignServInterface;

    @ApiOperation(value = "client端测试")
    @GetMapping("/client/democlient/test/{name}")
    public String clientTest(@PathVariable("name") String name){

        ApplicationContext applicationContext = ApplicationContextUtils.getApplicationContext();

        LogAop logAop = applicationContext.getBean(LogAop.class);
        log.info("aop实例：{}",logAop.toString());
        String service = "";
        try {
            String str = feignServInterface.servTest(name);
            service += str;
        }catch (Exception e){
            e.printStackTrace();
        }

        return "clienttest para :"+name +". demoservice para : "+ service + ",当前配置文件数据：" + val;
    }

    @ApiOperation(value = "client端starter测试")
    @GetMapping("/client/democlient/starterTest")
    public String starterTest(){
        personService.sayHello();
        return "测试成功";
    }

}
