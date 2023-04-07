package com.hzq.flow.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author Huangzq
 * @description
 * @date 2023/4/7 10:50
 */
@Slf4j
public class ThreadFlow {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture.supplyAsync(()->{
            log.info("逻辑运行");
            return 10;
        }).whenComplete((integer, throwable) -> {
            log.info("获取结果{}",integer);
        }).exceptionally(throwable -> {
            log.error("异常",throwable);
            return 30;
        }).get(10, TimeUnit.MILLISECONDS);
    }
}
