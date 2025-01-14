package cn.lsy.pipi.openfeign;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(Feg)
public @interface EnableFeignClients {
}
