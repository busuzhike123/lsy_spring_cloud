package cn.lsy.pipi.registry;

import org.springframework.cloud.client.serviceregistry.AbstractAutoServiceRegistration;
import org.springframework.cloud.client.serviceregistry.AutoServiceRegistrationProperties;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;
import org.springframework.context.ApplicationEvent;

public class PipiAutoServiceRegistration extends AbstractAutoServiceRegistration {

    private PipiRegistration pipiRegistration;
    protected PipiAutoServiceRegistration(ServiceRegistry serviceRegistry, PipiRegistration registration) {
        super(serviceRegistry, null);
        this.pipiRegistration = registration;
    }

    @Override
    protected Object getConfiguration() {
        return pipiRegistration.getPipiDiscoveryProperties();
    }

    @Override
    protected boolean isEnabled() {
        return true;
    }

    @Override
    protected Registration getRegistration() {
        if (pipiRegistration.getPort() < 0) {
            //设置服务端口
            pipiRegistration.setPort(this.getPort().get());
        }
        return pipiRegistration;
    }

    @Override
    protected Registration getManagementRegistration() {
        return null;
    }

}
