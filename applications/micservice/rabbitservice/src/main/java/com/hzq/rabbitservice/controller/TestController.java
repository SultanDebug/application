package com.hzq.rabbitservice.controller;

import com.hzq.common.aop.ResultResponse;
import com.hzq.rabbitservice.provider.TestProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * mq接口
 * @author Huangzq
 * @title: TestController
 * @projectName applications
 * @date 2019/12/13 16:20
 */
@RestController
@Api(tags = "rabbit测试")
@Slf4j
public class TestController {
    @Autowired
    private TestProvider provider;

    @ApiOperation("mq测试")
    @GetMapping("/mq")
    public ResultResponse<String> testMq(@RequestParam("msg") String msg){
        provider.sendMsg(msg);
        return ResultResponse.success("success");
    }
}
