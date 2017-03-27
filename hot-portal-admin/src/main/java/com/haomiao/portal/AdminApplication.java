package com.haomiao.portal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


@SpringBootApplication
public class AdminApplication  extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }
    /**
     * 配置拦截器
     * @author LJH
     * @param registry
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserSecurityInterceptor()).addPathPatterns("/Video/**");
    }
}
