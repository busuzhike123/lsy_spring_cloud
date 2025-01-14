package cn.lsy.pipi.discovery;

import cn.lsy.pipi.PipiDiscoveryProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PipiDiscoveryAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public PipiDiscoveryProperties pipiDiscoveryProperties() {
        return new PipiDiscoveryProperties();
    }

    @Bean
    public PipiDiscoveryClient pipiDiscoveryClient(PipiDiscoveryProperties pipiDiscoveryProperties) {
        return new PipiDiscoveryClient(pipiDiscoveryProperties);
    }

}
