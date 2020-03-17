package com.hzq.provider.controller;

import com.hzq.providerservice.ProviderInterface;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-03-17
 */
@RestController
public class ProviderController implements ProviderInterface {
    @Override
    public String provider(@PathVariable("msg") String msg) {
        return "provider:"+msg;
    }
}
