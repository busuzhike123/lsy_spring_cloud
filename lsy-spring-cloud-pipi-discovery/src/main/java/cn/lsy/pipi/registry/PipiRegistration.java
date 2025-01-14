package cn.lsy.pipi.registry;

import cn.lsy.pipi.PipiDiscoveryProperties;
import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.serviceregistry.Registration;

import java.net.URI;
import java.util.Map;

public class PipiRegistration implements Registration {


    private PipiDiscoveryProperties pipiDiscoveryProperties;

    public PipiRegistration(PipiDiscoveryProperties pipiDiscoveryProperties) {
        this.pipiDiscoveryProperties = pipiDiscoveryProperties;
    }

    @Override
    public String getServiceId() {
        return pipiDiscoveryProperties.getService();
    }

    @Override
    public String getHost() {
        return pipiDiscoveryProperties.getIp();
    }

    @Override
    public int getPort() {
        return pipiDiscoveryProperties.getPort();
    }

    public void setPort(int port) {
        this.pipiDiscoveryProperties.setPort(port);
    }

    @Override
    public boolean isSecure() {
        return pipiDiscoveryProperties.isSecure();
    }

    @Override
    public URI getUri() {
        return DefaultServiceInstance.getUri(this);
    }

    @Override
    public Map<String, String> getMetadata() {
        return null;
    }

    public PipiDiscoveryProperties getPipiDiscoveryProperties() {
        return pipiDiscoveryProperties;
    }
}
