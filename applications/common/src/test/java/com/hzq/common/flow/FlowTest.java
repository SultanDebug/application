package com.hzq.common.flow;

import com.alibaba.fastjson.JSONObject;
import com.hzq.common.load.CBean;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.slf4j.MDC;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.nio.file.Files;

import static com.hzq.common.utils.CommonConstants.TRACE_ID;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Flow.class, webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class FlowTest {
    @MockBean
    CBean cBean;

    @Before
    public void before() {
        try {
            String res = JSONObject.parseObject(Files.newInputStream(new File("src/test/resources/test/recall-response-test.json").toPath()), String.class);
            Mockito.when(cBean.test(Mockito.anyString())).thenReturn(res);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void shouldAnswerWithTrue() {
        MDC.put(TRACE_ID, "123");
        cBean.test("test");
    }
}
