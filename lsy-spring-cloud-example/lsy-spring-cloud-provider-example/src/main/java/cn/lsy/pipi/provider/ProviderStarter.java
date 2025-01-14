package cn.lsy.pipi.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ProviderStarter {
    public static void main(String[] args) {
        SpringApplication.run(ProviderStarter.class, args);
    }

    @RestController
    static class ProviderController {

        @RequestMapping("/echo")
        public String providerTest() {
            return "hello";
        }

    }
}
