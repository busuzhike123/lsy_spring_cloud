package cn.lsy.pipi.ribbon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

public class RibbonClientConfigurationRegistrar implements ImportBeanDefinitionRegistrar {

    public static final Logger logger  = LoggerFactory.getLogger(RibbonClientConfigurationRegistrar.class);

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata,
                                        BeanDefinitionRegistry registry) {
        Map<String, Object> annotationAttributes = metadata.getAnnotationAttributes(RibbonClients.class.getName());
        if(annotationAttributes != null && annotationAttributes.containsKey("defaultConfiguration")) {
            String name = "default." + metadata.getClassName();
            registerClientConfiguration(registry, name, annotationAttributes.get("defaultConfiguration"));
        }
    }

    private void registerClientConfiguration(BeanDefinitionRegistry registry, Object name,
                                             Object configuration) {
        BeanDefinitionBuilder builder = BeanDefinitionBuilder
                .genericBeanDefinition(RibbonClientSpecification.class);
        builder.addConstructorArgValue(name);
        builder.addConstructorArgValue(configuration);
        registry.registerBeanDefinition(name + ".RibbonClientSpecification",
                builder.getBeanDefinition());
        logger.info("自定义注册Bean：{}", name + ".RibbonClientSpecification");
    }
}
