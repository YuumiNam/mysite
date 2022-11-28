package com.bitacademy.mysite.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bitacademy.mysite.security.AuthInterceptor;
import com.bitacademy.mysite.security.AuthUserHandlerMethodArgumentResolver;
import com.bitacademy.mysite.security.LoginInterceptor;
import com.bitacademy.mysite.security.LogoutInterceptor;

@SpringBootConfiguration
@PropertySource("classpath:web/fileupload.properties")
public class WebConfig implements WebMvcConfigurer{
	
	@Autowired
	private Environment env;
	
	// Argument Resolver
	/**
	 *  <mvc:argument-resolvers>
	 *		<bean class="com.bitacademy.mysite.security.AuthUserHandlerMethodArgumentResolver"></bean>
	 *	</mvc:argument-resolvers>
	 */
	@Bean
	public HandlerMethodArgumentResolver authUserHandlerMethodArgumentResolver() {
		AuthUserHandlerMethodArgumentResolver handlerMethodArgumentResolver = new AuthUserHandlerMethodArgumentResolver();
		
		return handlerMethodArgumentResolver;
	}
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(authUserHandlerMethodArgumentResolver());
	}
	
	// Security Interceptors
	@Bean
	public HandlerInterceptor loginInterceptor() {
		LoginInterceptor handlerInterceptor = new LoginInterceptor();
		
		return handlerInterceptor;
	}
	
	@Bean
	public HandlerInterceptor logoutInterceptor() {
		LogoutInterceptor handlerInterceptor = new LogoutInterceptor();
		
		return handlerInterceptor;
	}
	
	@Bean
	public HandlerInterceptor authInterceptor() {
		AuthInterceptor handlerInterceptor = new AuthInterceptor();
		
		return handlerInterceptor;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry
			.addInterceptor(loginInterceptor())
			.addPathPatterns("/user/auth");
		
		registry
			.addInterceptor(logoutInterceptor())
			.addPathPatterns("/user/logout");
		
		registry
			.addInterceptor(authInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/user/auth")
			.excludePathPatterns("/user/logout")
			.excludePathPatterns("/assets/**");
	}
	
	// MVC Resource Mapping 저장한 파일과 url을 맵핑
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry
			.addResourceHandler(env.getProperty("fileupload.resourceMapping") + "/**")
			.addResourceLocations("file:" + env.getProperty("fileupload.uploadLocation") + "/");
		}
}
