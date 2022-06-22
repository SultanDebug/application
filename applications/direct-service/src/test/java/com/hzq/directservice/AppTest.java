package com.hzq.directservice;


import com.hzq.common.aop.ResultResponse;
import com.hzq.directservice.controller.TestController;
import com.hzq.rediscore.service.RedisService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

/**
 * Unit test for simple App.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppTest.class,webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//@ImportResource()
//@MapperScan("")
@Import({TestController.class})
public class AppTest 
{
    @MockBean
    private RedisService redisService;

    @Autowired
    private TestController controller;


    @Before
    public void before(){
        when(redisService.set(any(),any())).thenReturn(true);
        when(redisService.get("testKey")).thenReturn("asdasdasd");
    }

    @Test
    public void contextLoads() {
        try {
            ResultResponse<String> redis = controller.redis("11111");
            System.out.println(redis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
