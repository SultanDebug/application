package com.hzq.rediscore.lockaop;

import com.hzq.rediscore.service.RedisService;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * redis基础api 锁获取
 * @author Huangzq
 * @title: RedisLockUtil
 * @projectName applications
 * @date 2019/12/4 16:25
 */
@Slf4j
public class RedisLockUtil {
    private RedisService redisService;
    private String key;
    private int timeout = 5000;
    private int expire;
    private volatile boolean flag;

    public RedisLockUtil(RedisService redisService, String key,int timeout, int expire) {
        this.redisService = redisService;
        this.key = key;
        this.timeout = timeout;
        this.expire = expire;
    }

    public RedisLockUtil(RedisService redisService, String key, int expire) {
        this.redisService = redisService;
        this.key = key;
        this.expire = expire;
    }

    public synchronized boolean lock() throws InterruptedException {
        log.info("lock lockKey：{}，timeoutMsecs：{}，expireMsecs：{}", new Object[]{this.key, this.timeout, this.expire});
        int timeout1 = this.timeout;

        while(timeout1 >= 0) {
            long expire1 = System.currentTimeMillis() + (long)this.expire + 1L;
            String expireStr = String.valueOf(expire1);
            if (redisService.setNx(this.key, expireStr)) {
                this.flag = true;
                return true;
            }

            String val = (String) redisService.get(this.key);
            if (val != null && Long.parseLong(val) < System.currentTimeMillis()) {
                String expire2 = (String)redisService.getAndSet(this.key, expireStr);
                log.info("数据对比：{}，{}",expire2,expireStr);
                if (expire2 != null && expire2.equals(expireStr)) {
                    this.flag = true;
                    return true;
                }
            }

            timeout1 -= 100;
            TimeUnit.MILLISECONDS.sleep(100L);
        }

        return false;
    }

    public synchronized void unlock(){
        if(this.flag){
            this.redisService.delete(this.key);
            this.flag = false;
        }

    }
}
