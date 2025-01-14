package cn.lsy.pipi.ribbon;

import cn.lsy.pipi.PipiDiscoveryProperties;
import com.netflix.loadbalancer.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PipiServer extends Server {
    public PipiServer(String host, int port) {
        super(host, port);
    }
}
