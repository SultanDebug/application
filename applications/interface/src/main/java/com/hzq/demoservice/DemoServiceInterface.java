package com.hzq.demoservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Huangzq
 * @date 2019-03-25
 */
@FeignClient(value = "demo-service")
public interface DemoServiceInterface {
    @GetMapping("/demo/{name}")
    String getTest(@PathVariable("name") String name);

}
