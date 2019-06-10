package com.hzq.demoservice.util;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

}
