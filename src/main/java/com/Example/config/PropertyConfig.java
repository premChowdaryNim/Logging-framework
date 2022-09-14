package com.Example.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@EnableConfigurationProperties
@Getter
@Setter
public class PropertyConfig {

	@Value("${spring.application.name:SERVICE_NAME}")
	private String appName;
}
