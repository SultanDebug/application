package com.hzq.feign.controller;

import com.hzq.feignservice.FeignServInterface;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huangzq
 * @title: FeignServController
 * @projectName applications
 * @date 2019/11/20 15:59
 */
@RestController
@Api(tags = "feign服务端测试接口")
@Slf4j
public class FeignServController implements FeignServInterface {
    @Value("${test.val}")
    private String val;

    @Override
    @ApiOperation(value = "feign-service接口")
    public String servTest(@PathVariable("para") String para) {
        return "服务端调用成功："+val;
    }
}
