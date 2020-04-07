package com.hzq.netty.aopcondition.autoconfiguration.selector;

import com.hzq.netty.aopcondition.autoconfiguration.config.BeanService;
import com.hzq.netty.aopcondition.autoconfiguration.config.One;
import com.hzq.netty.aopcondition.autoconfiguration.config.Two;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Description: TODO
 * @Auth: Huangzq
 * @Date: Created in 2020-04-07
 */
public class BeanImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        List<String> beans = new ArrayList<>();

        Set<MethodMetadata> annotatedMethods = importingClassMetadata.getAnnotatedMethods(BeanService.class.getName());

        

        return new String[]{One.class.getName(), Two.class.getName()};
    }
}
