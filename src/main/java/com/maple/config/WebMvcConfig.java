package com.maple.config;

import com.maple.Inteceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName:WebMvcConfig
 * Package: com.maple.config
 * Description:
 * Author maple
 * Create 2023-01-11
 * Version: v1.0
 */
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/employee/login",
                        "/employee/logout",
                        "/backend/**",
                        "/front/**"
                );
    }
}
