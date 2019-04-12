package com.hzq.demoservice.controller;

import com.hzq.demoservice.util.RedisUtil;
import com.netflix.discovery.converters.Auto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Huangzq
 * @date 2019-04-12
 */
@RestController
@Api("测试服务")
public class DemoController  {

    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation("测试方法")
    @GetMapping("/demo")
    public String demo(@RequestParam String str){

        redisUtil.set("test","val");

        return "hello:"+str+","+redisUtil.get("test");
    }
}
