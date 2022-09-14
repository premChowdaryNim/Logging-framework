package com.Example.intercep;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import com.Example.config.PropertyConfig;

@Component
public class LogIntercepter implements HandlerInterceptor {

	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public LogIntercepter(PropertyConfig propertyConfig) {
		this.propertyConfig = propertyConfig;
	}

	private PropertyConfig propertyConfig;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception
	{
		logger.info("Log Interceptor Start");
		logger.info("Access service: {} from: {} url: {} principal: {} Time: {}",
				propertyConfig.getAppName(), getClientIp(request), request.getRequestURL(),
				(request.getUserPrincipal() == null ? "": request.getUserPrincipal().getName()),
				Instant.now().toEpochMilli());
		request.setAttribute("startTime", System.currentTimeMillis());
		logger.info("LogInterceptor PreHandler(End)");
		return true;
		
	}
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,@Nullable Exception ex) throws Exception{
		logger.info("LogInterceptor after view is rendered (start)");
		long endTime = System.currentTimeMillis();
		long startTime = Long.parseLong(request.getAttribute("startTime")+"");
		logger.info("Total time taken to process request {} (in milliseconds(ms)){ }", request.getRequestURL(),(endTime-startTime)+ " ms");
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
