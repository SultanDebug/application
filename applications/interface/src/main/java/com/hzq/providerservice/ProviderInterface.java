package com.hzq.providerservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-17
 */
@FeignClient(value = "discovery-provider",contextId = "providerInterface")
public interface ProviderInterface {
    @GetMapping("/provider/{msg}")
    String provider(@PathVariable("msg") String msg);
}
