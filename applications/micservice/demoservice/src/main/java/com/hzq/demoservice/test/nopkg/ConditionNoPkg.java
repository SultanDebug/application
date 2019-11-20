package com.hzq.demoservice.test.nopkg;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

/**
 * @author Huangzq
 * @title: ConditionTest
 * @projectName applications
 * @date 2019/7/24 10:49
 */
@Component
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ConditionNoPkg {
    public void sayCondition(){
        System.out.println("I'm ConditionNoPkg");
    }
}
