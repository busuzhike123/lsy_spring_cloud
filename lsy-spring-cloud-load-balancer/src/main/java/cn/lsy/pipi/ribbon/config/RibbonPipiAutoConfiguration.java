package cn.lsy.pipi.ribbon.config;

import org.springframework.context.annotation.Configuration;
import cn.lsy.pipi.ribbon.RibbonClients;

@Configuration
@RibbonClients(defaultConfiguration = PipiRibbonClientConfiguration.class)
public class RibbonPipiAutoConfiguration {
}
