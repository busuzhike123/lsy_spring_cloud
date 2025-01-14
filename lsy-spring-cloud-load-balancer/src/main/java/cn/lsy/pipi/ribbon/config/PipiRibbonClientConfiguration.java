package cn.lsy.pipi.ribbon.config;

import cn.lsy.pipi.PipiDiscoveryProperties;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.ServerList;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import cn.lsy.pipi.ribbon.PipiServerList;

@Configuration
@AutoConfigureBefore(RibbonClientConfiguration.class)
public class PipiRibbonClientConfiguration {

    @Bean
    public ServerList<?> ribbonServerList(IClientConfig config,
                                          PipiDiscoveryProperties pipiDiscoveryProperties) {
        PipiServerList pipiServerList = new PipiServerList(pipiDiscoveryProperties);
        pipiServerList.initWithNiwsConfig(config);
        return pipiServerList;
    }


}
