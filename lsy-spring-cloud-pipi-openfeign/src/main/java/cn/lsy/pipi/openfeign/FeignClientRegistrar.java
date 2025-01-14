package cn.lsy.pipi.openfeign;

import cn.hutool.core.util.ClassUtil;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.ClassUtils;

import java.util.Set;

public class FeignClientRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        String packageName = ClassUtils.getPackageName(importingClassMetadata.getClassName());
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(packageName, FeignClient.class);
        for(Class<?> clazz : classes) {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass();
        }
    }
}
