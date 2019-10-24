package com.hzq.common.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
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

    @Around("execution(* com.hzq.*.controller.DemoClientController(..))")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        String className = point.getTarget().getClass().getSimpleName()+"."+point.getSignature().getName();

        log.info("执行{}，入参为：{}",className, JSONArray.toJSONString(paramsList(point)));

        Object result = point.proceed();

        log.info("执行{}，出参为：{}",className, JSON.toJSONString(result));

        return result;
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
