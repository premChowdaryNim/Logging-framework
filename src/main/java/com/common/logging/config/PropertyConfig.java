package com.common.logging.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
public class PropertyConfig {
    
    public PropertyConfig() {
        System.out.println("PropertyConfig class loaded");
    }

    @Value("${spring.application.name:SERVICE_NAME}")
    private String appName;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }
    
    
}
