package com.hzq.netty.aopcondition.autoconfiguration.registra;

import com.hzq.netty.aopcondition.autoconfiguration.selector.BeanImportSelector;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Huangzq
 * @title: Test
 * @projectName applications
 * @date 2019/7/24 10:39
 */
@Configuration
@Import(BeanRegist.class)
@ComponentScan(basePackages = {"com.hzq.netty.aopcondition.autoconfiguration.registra"})
public class TestRegist {

    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestRegist.class);

        String [] strings = context.getBeanDefinitionNames();

        for (String string : strings) {
            System.out.println(string);
        }


        System.out.println(context.getEnvironment().getProperty("os.name"));

    }
}
