package cn.lsy.pipi.ribbon;

import cn.hutool.http.HttpUtil;
import cn.lsy.pipi.PipiDiscoveryProperties;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractServerList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PipiServerList extends AbstractServerList<PipiServer> {

    public static final Logger logger = LoggerFactory.getLogger(PipiServerList.class);

    private PipiDiscoveryProperties pipiDiscoveryProperties;

    private String serviceId;

    public PipiServerList(PipiDiscoveryProperties pipiDiscoveryProperties) {
        this.pipiDiscoveryProperties = pipiDiscoveryProperties;
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {
        this.serviceId = iClientConfig.getClientName();
    }

    @Override
    public List<PipiServer> getInitialListOfServers() {
        return getServer();
    }

    @Override
    public List<PipiServer> getUpdatedListOfServers() {
        return getServer();
    }

    private List<PipiServer> getServer() {
        Map<String, Object> param = new HashMap<>();
        param.put("serviceName", serviceId);

        String response = HttpUtil.get(pipiDiscoveryProperties.getServerAddr() + "/list", param);
        logger.info("query service instance, serviceId: {}, response: {}", serviceId, response);
        return JSON.parseArray(response).stream().map(hostInfo -> {
            String ip = ((JSONObject) hostInfo).getString("ip");
            Integer port = ((JSONObject) hostInfo).getInteger("port");
            return new PipiServer(ip, port);
        }).collect(Collectors.toList());
    }

    public PipiDiscoveryProperties getPipiDiscoveryProperties() {
        return pipiDiscoveryProperties;
    }

    public String getServiceId() {
        return serviceId;
    }
}
