package com.hzq.demoservice.controller;

import com.hzq.demoservice.config.RedisCon;
import com.hzq.demoservice.ssdb.core.SSDB;
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

    @Autowired
    private RedisCon redisCon;

    @ApiOperation("redis 性能测试")
    @GetMapping("/redis")
    public String redis(@RequestParam String str){

        System.out.println(redisCon);

        redisUtil.set("test","val");

        return "hello:"+str+","+redisUtil.get("test");
    }

    @ApiOperation("ssdb 性能测试")
    @GetMapping("/ssdb")
    public String ssdb(@RequestParam String str) throws Exception {

        SSDB ssdb = new SSDB("192.168.1.40",8888);

        ssdb.set("test","testVal");

        return "hello:"+str+","+ssdb.get("test");
    }

}
