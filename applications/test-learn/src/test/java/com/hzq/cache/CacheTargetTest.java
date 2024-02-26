package com.hzq.cache;

import org.springframework.stereotype.Service;

/**
 * @author Huangzq
 * @description
 * @date 2023/7/21 17:50
 */
@Service
public class CacheTargetTest implements CacheTestInterface {
    @LocalCache(prefix = "type-prefix")
    public String test(String query, String filter) {
        return "service";
    }
}
