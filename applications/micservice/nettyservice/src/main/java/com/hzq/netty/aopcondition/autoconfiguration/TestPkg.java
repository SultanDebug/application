package com.hzq.netty.aopcondition.autoconfiguration;

import com.hzq.netty.aopcondition.config.ImportTest;
import com.hzq.netty.aopcondition.nopkg.ConditionNoPkg;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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
//@Import()
@ComponentScan(basePackages = {"com.hzq.netty.aopcondition.autoconfiguration"})
public class TestPkg {

    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestPkg.class);

        String [] strings = context.getBeanDefinitionNames();

        for (String string : strings) {
            System.out.println(string);
        }


        System.out.println(context.getEnvironment().getProperty("os.name"));

    }
}
