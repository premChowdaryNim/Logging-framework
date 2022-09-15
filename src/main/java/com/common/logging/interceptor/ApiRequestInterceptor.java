package com.common.logging.interceptor;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.common.logging.config.PropertyConfig;

@Component
public class ApiRequestInterceptor extends HandlerInterceptorAdapter{

    private PropertyConfig propertyConfig;
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    
    public ApiRequestInterceptor(PropertyConfig propertyConfig) {
        this.propertyConfig = propertyConfig;
    }
        
    @Override
    public boolean preHandle(
      HttpServletRequest request, 
      HttpServletResponse response, 
      Object handler) {
        logger.info("Log Interceptor Start");
        logger.info("Access service: {} from: {} url: {} principal: {} Time: {}",
                propertyConfig.getAppName(), getClientIp(request), request.getRequestURL(),
                (request.getUserPrincipal() == null ? "": request.getUserPrincipal().getName()),
                Instant.now().toEpochMilli());
        request.setAttribute("startTime", System.currentTimeMillis());
        logger.info("LogInterceptor PreHandler(End)");
        return true;
    }
    
    private String getClientIp(HttpServletRequest request) {
        String clientXForwardForIp = request.getHeader("x-forwarded-for");
        logger.debug("clientXForwardedForIp :: {}",clientXForwardForIp);
        if(StringUtils.isEmpty(clientXForwardForIp)) {
            return getIPFromXForwardedHeader(clientXForwardForIp);
        }
        else {
            return StringUtils.isEmpty(request.getRemoteAddr())? request.getRemoteAddr():"";
        }
    }
    
    private String getIPFromXForwardedHeader(String header) {
        return header.split(" *, *")[0];
    }
}
