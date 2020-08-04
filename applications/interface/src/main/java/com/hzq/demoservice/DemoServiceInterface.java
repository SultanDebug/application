package com.hzq.demoservice;

import com.hzq.common.aop.ResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程接口调用测试
 * @author Huangzq
 * @date 2019-03-25
 */
@FeignClient(value = "demo-service",contextId = "demoServiceInterface")
public interface DemoServiceInterface {
    @GetMapping("/demo/{name}")
    ResultResponse<String> getTest(@PathVariable("name") String name);

}
