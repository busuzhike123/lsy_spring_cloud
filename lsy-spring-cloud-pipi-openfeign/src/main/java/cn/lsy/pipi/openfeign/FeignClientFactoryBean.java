package cn.lsy.pipi.openfeign;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.cglib.proxy.Factory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class FeignClientFactoryBean implements FactoryBean<Object>, ApplicationContextAware {
    @Override
    public Object getObject() throws Exception {
        return null;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
