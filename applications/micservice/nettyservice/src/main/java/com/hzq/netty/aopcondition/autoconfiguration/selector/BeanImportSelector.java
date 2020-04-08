package com.hzq.netty.aopcondition.autoconfiguration.selector;

import com.hzq.netty.aopcondition.autoconfiguration.config.BeanService;
import com.hzq.netty.aopcondition.autoconfiguration.config.One;
import com.hzq.netty.aopcondition.autoconfiguration.config.Two;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.MethodMetadata;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;

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

        Set<String> annotationTypes = importingClassMetadata.getAnnotationTypes();

        return new String[]{One.class.getName(), Two.class.getName()};
    }
}
