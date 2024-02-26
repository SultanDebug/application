package com.hzq.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author Huangzq
 * @description
 * @date 2023/7/21 17:13
 */
@Aspect
@Slf4j
@Component
public class LocalCacheAspect {

    private static Cache<String, Object> caffeine = Caffeine.newBuilder()
            //cache的初始容量
            .initialCapacity(10)
            //cache最大缓存数
            .maximumSize(1000)
            //设置写缓存后n秒钟过期
            .expireAfterWrite(10, TimeUnit.SECONDS)
            .build();

    @Around("@annotation(com.hzq.cache.LocalCache)")
    public Object proccess(ProceedingJoinPoint point) {

        // 获取注解的属性值
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        LocalCache localCache = AnnotationUtils.findAnnotation(methodSignature.getMethod(), LocalCache.class);
        String prefix = localCache.prefix();

        //获取参数
        Object[] args = point.getArgs();
        //直接参数顺序参与签名
        String collect = Arrays.stream(args).map(Object::toString).collect(Collectors.joining());
        String code = DigestUtils.md5DigestAsHex(collect.getBytes(StandardCharsets.UTF_8));

        //缓存key=前缀名+参数签名
        String key = prefix + ":" + code;
        Object ifPresent = caffeine.getIfPresent(key);

        if (ifPresent != null) {
            log.info("缓存命中：{}", collect);
            return ifPresent;
        }

        try {
            //缓存新增
            Object proceed = point.proceed();
            caffeine.put(key, proceed);
            return proceed;
        } catch (Throwable e) {
            log.error("方法执行异常", e);
            return null;
        }
    }
}
