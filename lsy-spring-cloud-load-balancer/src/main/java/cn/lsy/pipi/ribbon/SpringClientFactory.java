package cn.lsy.pipi.ribbon;

import org.springframework.cloud.context.named.NamedContextFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import cn.lsy.pipi.ribbon.config.RibbonClientConfiguration;

public class SpringClientFactory extends NamedContextFactory<RibbonClientSpecification> {

    public static final String NAMESPACE = "cn/lsy/pipi/ribbon";

    public SpringClientFactory() {
        super(RibbonClientConfiguration.class, NAMESPACE, "ribbon.client.name");
    }

    @Override
    protected AnnotationConfigApplicationContext getContext(String name) {
        return super.getContext(name);
    }

    @Override
    public <T> T getInstance(String name, Class<T> type) {
        return super.getInstance(name, type);
    }
}
