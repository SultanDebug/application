package com.hzq.common.load;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author Huangzq
 * @description
 * @date 2023/11/7 15:45
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoadApp.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class LoadTest {
    @Autowired
    CBean cBean;

    @Test
    public void test() {
        cBean.test("test");
    }

}
