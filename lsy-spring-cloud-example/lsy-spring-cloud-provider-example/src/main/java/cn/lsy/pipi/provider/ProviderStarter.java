package cn.lsy.pipi.provider;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ProviderStarter {
    public static void main(String[] args) {
        SpringApplication.run(ProviderStarter.class, args);
    }

    @RestController
    static class ProviderController {

        @SentinelResource(value = "echo", blockHandler = "queryUserByUserNameBlock")
        @RequestMapping("/echo")
        public String providerTest() {
            return "hello";
        }

        public String queryUserByUserNameBlock(BlockException ex) {
            //打印异常
            ex.printStackTrace();
            return "资源访问被限流";
        }

    }
}
