package com.hzq.providerservice;

import com.hzq.common.aop.ResultResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 远程提供者测试
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-17
 */
@FeignClient(value = "discovery-provider",contextId = "providerInterface")
public interface ProviderInterface {
    @GetMapping("/provider/{msg}")
    ResultResponse<String> provider(@PathVariable("msg") String msg);
}
