package com.hzq.directservice.controller;

import com.hzq.common.aop.ResultResponse;
import com.hzq.rediscore.lockaop.RedisLock;
import com.hzq.rediscore.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @Author: Huangzq
 * @Date: 2022/3/9 9:32
 */
@RestController
@Api(tags = "无配置中心启动")
@Slf4j
public class TestController {
    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "redis锁测试")
    @GetMapping("/direct/redis")
    @RedisLock(lockKey = "lockKey",expire = 60000)
    public ResultResponse<String> redis(String val) throws InterruptedException {
        redisService.set("testKey",val);
        TimeUnit.SECONDS.sleep(1L);
        return ResultResponse.success((String) redisService.get("testKey"));
    }
}
