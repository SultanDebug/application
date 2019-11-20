package com.hzq.demoservice.util;

import lombok.Data;
import org.springframework.stereotype.Component;


import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Huangzq
 * @title: ProduceThreadPool
 * @projectName applications
 * @date 2019/6/10 15:06
 */
@Component
@Data
public class ProduceThreadPool {

    public static ConcurrentHashMap<Long,Thread> pool = new ConcurrentHashMap<>();

    public static ExecutorService executorService = Executors.newFixedThreadPool(10);

    public static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

}
