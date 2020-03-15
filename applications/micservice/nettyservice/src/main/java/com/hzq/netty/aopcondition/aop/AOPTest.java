package com.hzq.netty.aopcondition.aop;

import com.hzq.netty.aopcondition.aop.annotation.ServiceAop;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Huangzq
 * @title: AOPTest
 * @projectName applications
 * @date 2019/8/7 9:35
 */
@Configuration
@ComponentScan(basePackages = {"com.hzq.netty.aopcondition.aop"})
@Slf4j
@EnableAspectJAutoProxy
public class AOPTest {
    @Test
    public void test() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AOPTest.class);

        /*String [] strings = context.getBeanDefinitionNames();

        for (String string : strings) {
            System.out.println(string);
        }*/

        /*AspectTest aspectTest = context.getBean(AspectTest.class);
        log.info("方法开始");
        aspectTest.opration("长江长江，我是黄河");
        log.info("方法结束");*/

        ServiceAop aspectTest = context.getBean(ServiceAop.class);
        log.info("方法开始");
        aspectTest.targetMethod("长江长江，我是黄河");
        log.info("方法结束");
    }
}
