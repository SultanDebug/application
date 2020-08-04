package com.hzq.provider.controller;

import com.hzq.common.aop.ResultResponse;
import com.hzq.providerservice.ProviderInterface;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * 服务提供者
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-17
 */
@RestController
public class ProviderController implements ProviderInterface {

    private final Environment environment;

    public ProviderController(Environment environment) {
        this.environment = environment;
    }

    private String getPort(){
        return this.environment.getProperty("local.server.port");
    }

    private String[] getProps(){
        return this.environment.getActiveProfiles();
    }

    @Override
    public ResultResponse<String> provider(@PathVariable("msg") String msg) {
        return ResultResponse.success("provider:port:"+getPort()+"***profiles:"+ Arrays.toString(getProps()) +":"+msg);
    }
}
