package com.hzq.common.log;

import com.hzq.common.utils.UserUtils;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import java.util.UUID;

import static com.hzq.common.utils.CommonConstants.TRACE_ID;


/**
 * feign调用拦截，上下文参数透传
 *
 * @author Huangzq
 * @description
 * @date 2022/8/29 15:28
 */
@Configuration
@Slf4j
public class FeignInterceptor {
    @Bean
    public RequestInterceptor headerInterceptor() {
        return requestTemplate -> {

            log.info("拦截器所在线程："+ Thread.currentThread().getId());
            requestTemplate.header("user", UserUtils.getUser());

            String traceId = MDC.get(TRACE_ID);
            if (!StringUtils.isEmpty(traceId)) {
                requestTemplate.header(TRACE_ID, traceId);
            } else {
                requestTemplate.header(TRACE_ID, UUID.randomUUID().toString().replace("-", ""));
            }

        };
    }
}
