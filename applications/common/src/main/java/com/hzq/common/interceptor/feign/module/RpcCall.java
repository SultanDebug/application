package com.hzq.common.interceptor.feign.module;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * @Desc TODO
 * @Author Huang
 * @Date 2022/4/17 14:23
 **/
//@Slf4j
public class RpcCall {
    private static final Logger log = LoggerFactory.getLogger(RpcCall.class);

    public static String call(String host, String port, Method method, Object[] args) {
        log.info("start call {}:{}/{}==>{}", host, port, method.getName(), StringUtils.join(args, ","));
        String res = host + ":" + port + "/" + method.getName() + "==>" + StringUtils.join(args, ",");
        log.info("call finished");
        return res;
    }
}
