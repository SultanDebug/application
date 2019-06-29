package com.hzq.gateway.filter;

import com.google.common.base.Charsets;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Huangzq
 * @title: SecurityFilter
 * @projectName applications
 * @date 2019/6/29 15:53
 */
@Component
public class SecurityFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        String source = request.getHeader("source");

        if("success".equals(source)){
            context.addZuulRequestHeader("output","output:"+source);
        }else{
            fail(context,"校验失败");
        }

        return null;
    }

    /**
     * 返回逻辑统一处理
     */
    private void fail(RequestContext ctx, String message) {
        HttpServletResponse response = ctx.getResponse();
        // 设置字符集
        response.setCharacterEncoding(Charsets.UTF_8.name());
        // 设置相应格式
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        ctx.setResponse(response);
        // 过滤该请求，不往下级服务去转发请求，到此结束
        ctx.setSendZuulResponse(false);
//        WebApiResponse rsp = WebApiResponse.error(errorCode, message);
        ctx.setResponseBody(message);
    }

}
