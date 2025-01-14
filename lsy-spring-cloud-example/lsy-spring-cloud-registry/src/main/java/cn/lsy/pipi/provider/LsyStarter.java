package cn.lsy.pipi.provider;


import cn.lsy.pipi.provider.controller.PipiDiscoveryController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LsyStarter {
    public static void main(String[] args) {
        SpringApplication.run(LsyStarter.class, args);
        System.out.println("服务实例：" + PipiDiscoveryController.serviceInstances.toString());
    }
}
