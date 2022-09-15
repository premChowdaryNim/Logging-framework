package com.common.logging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.common.logging.interceptor.ApiRequestInterceptor;

@Configuration
//@ConfigurationProperties(prefix = "logging.custom")
@ComponentScan
public class CommonLog implements WebMvcConfigurer{
    
    @Autowired
    ApiRequestInterceptor apiRequestInterceptor;
    
//    @Bean
//    public ApiRequestInterceptor apiReqInterceptor() {
//        return new ApiRequestInterceptor();
//    }
    
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiRequestInterceptor);
    }
    
}
