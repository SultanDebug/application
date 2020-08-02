package com.hzq.rediscore.lockaop;

import com.hzq.rediscore.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 分布式锁切面
 * @author Huangzq
 * @title: RedisLockAop
 * @projectName applications
 * @date 2019/12/4 9:38
 */
@Aspect
@Slf4j
@Component
public class RedisLockAop {

    @Autowired
    private RedisService redisService;


    @Around("@annotation(com.hzq.rediscore.lockaop.RedisLock)")
    public Object process(ProceedingJoinPoint point) throws Throwable{
        MethodSignature signature = (MethodSignature)point.getSignature();
        Method method = signature.getMethod();

        RedisLock redisLock = method.getAnnotation(RedisLock.class);

        RedisLockUtil redisLockUtil = new RedisLockUtil(this.redisService,redisLock.lockKey(),redisLock.timeout(),redisLock.expire());

        Object result ;
        log.info("开始获取锁：{}",redisLock.lockKey());
        try {
            if(!redisLockUtil.lock()){
                return "锁获取失败";
            }
            result = point.proceed();
        }catch (Exception e){
            e.printStackTrace();
            return "方法执行异常";
        }finally {
            redisLockUtil.unlock();
            log.info("锁释放：{}",redisLock.lockKey());
        }
        return result;
    }
}
