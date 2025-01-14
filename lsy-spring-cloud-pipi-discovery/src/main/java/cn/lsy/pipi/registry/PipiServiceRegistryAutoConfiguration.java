package cn.lsy.pipi.registry;


import cn.lsy.pipi.PipiDiscoveryProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(value = "spring.cloud.service-registry.auto-registration.enabled", matchIfMissing = true)
public class PipiServiceRegistryAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public PipiDiscoveryProperties pipiDiscoveryProperties() {
        return new PipiDiscoveryProperties();
    }

    @Bean
    public PipiRegistration pipiRegistration(PipiDiscoveryProperties pipiDiscoveryProperties) {
        return new PipiRegistration(pipiDiscoveryProperties);
    }

    @Bean
    public PipiServiceRegistry pipiServiceRegistry(PipiDiscoveryProperties pipiDiscoveryProperties) {
        return new PipiServiceRegistry(pipiDiscoveryProperties);
    }

    @Bean
    public PipiAutoServiceRegistration pipiAutoServiceRegistration(PipiServiceRegistry pipiServiceRegistry, PipiRegistration pipiRegistration) {
        return new PipiAutoServiceRegistration(pipiServiceRegistry, pipiRegistration);
    }

}
