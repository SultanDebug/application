package com.hzq.common.bean;

import com.google.common.collect.Lists;
import com.hzq.common.load.CBean;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

/**
 * @author Huangzq
 * @description
 * @date 2023/11/7 15:45
 */
@RunWith(SpringRunner.class)
@Import({CBean.class})
public class BeanTest {
    @MockBean
    private CBean cBean;

    @Before
    public void before() {
        Mockito.when(cBean.test1(Mockito.anyString())).thenReturn(
                Lists.newArrayList("a", "b").stream().map(o -> o + "/").collect(Collectors.toList())
        );
    }

    @Test
    public void test() {
        System.out.println(cBean.test1("test"));
    }


    @Test
    public void tmp() {
        char s = '㍿';
        char s1 = '☯';
        String s2 = "\uD83D\uDCA3";
        System.out.println(s);
        System.out.println(s2);
        System.out.println((int) s);

    }

}
