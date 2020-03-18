package com.hzq.common.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.hzq.common.utils.UserUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Huangzq
 * @title: LogAop
 * @projectName applications
 * @date 2019/10/23 10:35
 */
@Aspect
@Slf4j
@Component
public class LogAop {

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 切点
     */
    /*@Pointcut("execution(public * com.hzq.*.controller..*(..))")
    public void httpResponse() {
    }


    @Before("httpResponse()")
    public void doBefore(JoinPoint joinPoint){
        //开始计时
        startTime.set(System.currentTimeMillis());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        //打印请求的内容
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));//获取请求头中的User-Agent
        log.info("接口路径：{}" , request.getRequestURL().toString());
        log.info("浏览器：{}", userAgent.getBrowser().toString());
        log.info("浏览器版本：{}",userAgent.getBrowserVersion());
        log.info("操作系统: {}", userAgent.getOperatingSystem().toString());
        log.info("IP : {}" , request.getRemoteAddr());
        log.info("请求类型：{}", request.getMethod());
        log.info("类方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("请求参数 : {} " + Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(returning = "ret" , pointcut = "httpResponse()")
    public void doAfterReturning(Object ret){
        //处理完请求后，返回内容
        log.info("方法返回值：{}" , ret);
        log.info("方法执行时间：{}毫秒", (System.currentTimeMillis() - startTime.get()));
    }*/

    @Around("execution(public * com.hzq.*.controller..*(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        String className = point.getTarget().getClass().getSimpleName()+"."+point.getSignature().getName();

        try {
            /**
             * 日志打印
             */
            log.info("执行{}，入参为：{}",className, JSONArray.toJSONString(paramsList(point)));

            /**
             * threadlocal设置
             */
            RequestAttributes ra = RequestContextHolder.getRequestAttributes();
            ServletRequestAttributes sra = (ServletRequestAttributes) ra;
            HttpServletRequest request = sra.getRequest();
            UserUtils.setUser(request.getHeader("user"));

            Object result = point.proceed();

            log.info("执行{}，出参为：{}",className, JSON.toJSONString(result));

            UserUtils.removeUser();
            return result;
        }catch (Exception e){
            e.printStackTrace();
            UserUtils.removeUser();
        }

        return null;
    }

    private List<Object> paramsList(ProceedingJoinPoint point){
        List<Object> params = new ArrayList<>();

        Object[] objects = point.getArgs();

        for (Object object : objects) {
            if(! (object instanceof HttpServletRequest) && ! (object instanceof HttpServletResponse)){
                params.add(object);
            }
        }

        return params;

    }

}
