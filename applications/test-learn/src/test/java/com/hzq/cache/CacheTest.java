package com.hzq.cache;

import com.alibaba.fastjson.JSONObject;
import com.hzq.TestLearnApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Huangzq
 * @description
 * @date 2023/7/21 16:33
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestLearnApp.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class CacheTest {

    @Autowired
    private CacheTargetTest cacheTargetTest;

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {

            JSONObject object = new JSONObject();
            object.put("key-" + i, "val-" + i);

            CacheUtil.put("key-" + i, object);
        }


        for (int i = 0; i < 10; i++) {
            JSONObject v1 = CacheUtil.getOrDefault("key-" + i, o -> new JSONObject());
            System.out.println(v1);
        }

        System.out.println(CacheUtil.getIfPresent("key", String.class));
    }

    @Test
    public void test() {
        String test = cacheTargetTest.test("query==", "filter==");
        System.out.println(test);
        String test1 = cacheTargetTest.test("query==", "filter==");
        System.out.println(test1);
    }
}
