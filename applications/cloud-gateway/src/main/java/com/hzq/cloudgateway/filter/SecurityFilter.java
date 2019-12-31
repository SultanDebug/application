package com.hzq.cloudgateway.filter;

import com.hzq.common.utils.UserUtils;
import com.hzq.feignservice.FeignServInterface;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author Huangzq
 * @title: SecurityFilter
 * @projectName applications
 * @date 2019/9/16 14:46
 */
@Component
public class SecurityFilter implements GlobalFilter, Ordered {

    private static final Log log = LogFactory.getLog(GatewayFilter.class);
    @Autowired
    private FeignServInterface feignServInterface;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入过滤器");
        ServerHttpRequest serverHttpRequest = exchange.getRequest();

        ServerHttpResponse serverHttpResponse = exchange.getResponse();

        exchange.getAttributes().put("startTime",System.currentTimeMillis());

        feignServInterface.servTest("test");

        return chain.filter(exchange).then(Mono.fromRunnable(()->{
            long start = exchange.getAttribute("startTime");
            log.info(exchange.getRequest().getURI().getRawPath()+":"+(start-System.currentTimeMillis()));
        }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
