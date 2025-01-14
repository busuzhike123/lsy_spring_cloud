package cn.lsy.pipi.provider.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class PipiDiscoveryController {

    public static final Logger logger = LoggerFactory.getLogger(PipiDiscoveryController.class);
    public static final Map<String, Set<Server>> serviceInstances  = new ConcurrentHashMap<>();

    @RequestMapping("/register")
    public Boolean register(@RequestParam("serviceName") String serviceName,
                            @RequestParam("ip") String ip,
                            @RequestParam("port") String port) {
        Set<Server> instances = serviceInstances.computeIfAbsent(serviceName, o -> new HashSet<>());
        instances.add(new Server(ip, Integer.parseInt(port)));
        logger.info("服务注册，服务名称：【{}】，服务实例：【{}】", serviceName, ip + ":" + port);
        return Boolean.TRUE;
    }

    @RequestMapping("/deregister")
    public Boolean deregister(@RequestParam("serviceName") String serviceName,
                              @RequestParam("ip") String ip,
                              @RequestParam("port") String port) {
        Set<Server> instances = serviceInstances.computeIfAbsent(serviceName, o -> new HashSet<>());
        instances.remove(new Server(ip, Integer.parseInt(port)));
        logger.info("服务下线，服务名称：【{}】，服务实例：【{}】，剩余服务实例：【{}】", serviceName, ip + ":" + port, instances.toString());
        return Boolean.TRUE;
    }

    @RequestMapping("/getAll")
    public String getAll() {
        return serviceInstances.toString();
    }

    @RequestMapping("/list")
    public Set<Server> list(@RequestParam("serviceName") String serviceName) {
        Set<Server> serverSet = serviceInstances.get(serviceName);
        logger.info("list service, serviceName: {}, serverSet: {}", serviceName, serverSet.toString());
        return serverSet != null ? serverSet : Collections.emptySet();
    }

    public static class Server {
        private String ip;

        private Integer port;

        public Server(String ip, Integer port) {
            this.ip = ip;
            this.port = port;
        }

        public String getIp() {
            return ip;
        }

        public Integer getPort() {
            return port;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Server server = (Server) o;

            if (!ip.equals(server.ip)) return false;
            return port.equals(server.port);
        }

        @Override
        public int hashCode() {
            int result = ip.hashCode();
            result = 31 * result + port.hashCode();
            return result;
        }
    }


}
