package com.hzq.feignservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程服务接口测试
 * @author Huangzq
 * @title: FeignServInterface
 * @projectName applications
 * @date 2019/11/20 16:34
 */
@FeignClient(value = "feign-service",contextId = "feignServInterface")
public interface FeignServInterface {
    @GetMapping("/feignSev/{para}")
    String servTest(@PathVariable("para") String para);

    @GetMapping("/feignSevDb/{id}")
    String feignSevDb(@PathVariable("id") Integer id);

}
