package cn.lsy.pipi.registry;

import cn.hutool.http.HttpUtil;
import cn.lsy.pipi.PipiDiscoveryProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.serviceregistry.Registration;
import org.springframework.cloud.client.serviceregistry.ServiceRegistry;

import java.util.HashMap;
import java.util.Map;

public class PipiServiceRegistry implements ServiceRegistry<Registration> {

    public static final Logger logger = LoggerFactory.getLogger(PipiServiceRegistry.class);

    private PipiDiscoveryProperties pipiDiscoveryProperties;

    public PipiServiceRegistry(PipiDiscoveryProperties pipiDiscoveryProperties) {
        this.pipiDiscoveryProperties = pipiDiscoveryProperties;
    }

    @Override
    public void register(Registration registration) {
        Map<String, Object> param = new HashMap<>();
        param.put("serviceName", pipiDiscoveryProperties.getService());
        param.put("ip", pipiDiscoveryProperties.getIp());
        param.put("port", pipiDiscoveryProperties.getPort());

        String result = HttpUtil.post(pipiDiscoveryProperties.getServerAddr() + "/register", param);

        if (Boolean.parseBoolean(result)) {
            logger.info("register service successfully, serviceName: {}, ip: {}, port: {}",
                    pipiDiscoveryProperties.getService(), pipiDiscoveryProperties.getIp(), pipiDiscoveryProperties.getPort());
        } else {
            logger.error("register service failed, serviceName: {}, ip: {}, port: {}",
                    pipiDiscoveryProperties.getService(), pipiDiscoveryProperties.getIp(), pipiDiscoveryProperties.getPort());
            throw new RuntimeException("register service failed, serviceName");
        }
    }

    @Override
    public void deregister(Registration registration) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("serviceName", pipiDiscoveryProperties.getService());
        param.put("ip", pipiDiscoveryProperties.getIp());
        param.put("port", pipiDiscoveryProperties.getPort());

        String result = HttpUtil.post(pipiDiscoveryProperties.getServerAddr() + "/deregister", param);

        if (Boolean.parseBoolean(result)) {
            logger.info("register service successfully, serviceName: {}, ip: {}, port: {}",
                    pipiDiscoveryProperties.getService(), pipiDiscoveryProperties.getIp(), pipiDiscoveryProperties.getPort());
        } else {
            logger.error("register service failed, serviceName: {}, ip: {}, port: {}",
                    pipiDiscoveryProperties.getService(), pipiDiscoveryProperties.getIp(), pipiDiscoveryProperties.getPort());
            throw new RuntimeException("register service failed, serviceName");
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void setStatus(Registration registration, String status) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getStatus(Registration registration) {
        return null;
    }
}
