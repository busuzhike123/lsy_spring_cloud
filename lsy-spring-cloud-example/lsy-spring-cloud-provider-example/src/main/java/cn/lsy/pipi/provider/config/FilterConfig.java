package cn.lsy.pipi.provider.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

//@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean sentinelFilterRegistration() {
        FilterRegistrationBean<Filter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new Test1Filter());
        registration.addUrlPatterns("/*");
        registration.setName("sentinelFilter");
        registration.setOrder(1);
        return registration;
    }


    static class Test1Filter implements Filter {

        @Override
        public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
                throws IOException, ServletException {
            // TODO Auto-generated method stub
            HttpServletRequest request = (HttpServletRequest) arg0;
            System.out.println("自定义过滤器filter1触发,拦截url:" + request.getRequestURI());
            arg2.doFilter(arg0, arg1);
        }

    }

}
