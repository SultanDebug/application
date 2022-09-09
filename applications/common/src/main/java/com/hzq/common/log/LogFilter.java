package com.hzq.common.log;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

import static com.hzq.common.utils.CommonConstants.TRACE_ID;


/**
 * http请求上下文参数处理
 *
 * @author Huangzq
 * @description
 * @date 2022/8/29 09:17
 */
@Slf4j
@Order
@Component
public class LogFilter implements Filter {
    /**
     * http请求拦截，header中注入traceId
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            String header = req.getHeader(TRACE_ID);
            String traceId = StringUtils.isEmpty(header) ?
                    UUID.randomUUID().toString().replace("-", "")
                    : header;

            MDC.put(TRACE_ID, traceId);
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            MDC.remove(TRACE_ID);
        }
    }

}
