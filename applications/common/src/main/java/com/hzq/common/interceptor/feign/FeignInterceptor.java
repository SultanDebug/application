package com.hzq.common.interceptor.feign;

import com.hzq.common.utils.UserUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

/**
 * feign拦截器  token透传
 * @author Huangzq
 * @title: FeignInterceptor
 * @projectName applications
 * @date 2019/11/22 18:14
 */
@Slf4j
public class FeignInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        log.info("拦截器所在线程："+ Thread.currentThread().getId());
        requestTemplate.header("user",UserUtils.getUser());
    }
}
