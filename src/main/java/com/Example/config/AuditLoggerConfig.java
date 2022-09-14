package com.Example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.Example.intercep.LogIntercepter;


@Configuration
public class AuditLoggerConfig implements WebMvcConfigurer{

	@Autowired
	private PropertyConfig propertyConfig;
	
	public void addinterceptors(InterceptorRegistry registry) {
		LogIntercepter logInterceptor = new LogIntercepter(propertyConfig);
		registry.addInterceptor(logInterceptor);
	}
}
