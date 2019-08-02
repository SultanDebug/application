package com.hzq.demoservice.test;

import com.google.common.annotations.VisibleForTesting;
import com.hzq.demoservice.test.config.ConditionFilterTest;
import com.hzq.demoservice.test.config.ConditionTest;
import com.hzq.demoservice.test.config.ImportTest;
import com.hzq.demoservice.test.nopkg.ConditionNoPkg;
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
@Import(ImportTest.class)
@ComponentScan(basePackages = {"com.hzq.demoservice.test"})
public class TestPkg {

    @Autowired
    private ConditionNoPkg conditionNoPkg;

    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestPkg.class);

        String [] strings = context.getBeanDefinitionNames();

        for (String string : strings) {
            System.out.println(string);
        }
        /*ConditionTest conditionTest = context.getBean(ConditionTest.class);
        conditionTest.sayCondition();

        conditionNoPkg = context.getBean(ConditionNoPkg.class);
        conditionNoPkg.sayCondition();

        conditionNoPkg = context.getBean(ConditionNoPkg.class);
        conditionNoPkg.sayCondition();*/

        System.out.println(context.getEnvironment().getProperty("os.name"));

    }
}
