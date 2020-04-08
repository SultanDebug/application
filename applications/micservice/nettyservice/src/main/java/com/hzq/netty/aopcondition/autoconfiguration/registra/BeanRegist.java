package com.hzq.netty.aopcondition.autoconfiguration.registra;

import com.hzq.netty.aopcondition.autoconfiguration.config.One;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-04-07
 */
public class BeanRegist implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        RootBeanDefinition beanDefinition = new RootBeanDefinition(One.class);
        String uncapitalize = StringUtils.uncapitalize(One.class.getSimpleName());
        registry.registerBeanDefinition(uncapitalize,beanDefinition);
    }
}
