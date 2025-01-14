package cn.lsy.pipi.ribbon;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Configuration(proxyBeanMethods = false)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import(RibbonClientConfigurationRegistrar.class)
public @interface RibbonClients {

    Class<?>[] defaultConfiguration() default {};

}
