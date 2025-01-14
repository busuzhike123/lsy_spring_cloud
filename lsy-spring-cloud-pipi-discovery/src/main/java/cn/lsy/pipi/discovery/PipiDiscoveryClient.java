package cn.lsy.pipi.discovery;

import cn.hutool.http.HttpUtil;
import cn.lsy.pipi.PipiDiscoveryProperties;
import cn.lsy.pipi.PipiServiceInstance;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PipiDiscoveryClient implements DiscoveryClient {

    public static final Logger logger = LoggerFactory.getLogger(PipiDiscoveryClient.class);

    private PipiDiscoveryProperties pipiDiscoveryProperties;

    public PipiDiscoveryClient(PipiDiscoveryProperties pipiDiscoveryProperties) {
        this.pipiDiscoveryProperties = pipiDiscoveryProperties;
    }

    @Override
    public String description() {
        return null;
    }

    @Override
    public List<ServiceInstance> getInstances(String serviceId) {
        Map<String, Object> param = new HashMap<>();
        param.put("serviceName", serviceId);

        String response = HttpUtil.get(pipiDiscoveryProperties.getServerAddr() + "/list", param);
        logger.info("query service instance, serviceId: {}, response: {}", serviceId, response);
        return JSON.parseArray(response).stream().map(hostInfo -> {
            PipiServiceInstance serviceInstance = new PipiServiceInstance();
            serviceInstance.setServiceId(serviceId);
            String ip = ((JSONObject) hostInfo).getString("ip");
            Integer port = ((JSONObject) hostInfo).getInteger("port");
            serviceInstance.setHost(ip);
            serviceInstance.setPort(port);
            return serviceInstance;
        }).collect(Collectors.toList());
    }

    @Override
    public List<String> getServices() {
        String response = HttpUtil.get(pipiDiscoveryProperties.getServerAddr() + "/all");
        return JSON.parseArray(response, String.class);
    }
}
