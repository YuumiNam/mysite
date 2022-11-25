package com.bitacademy.mysite.config.web;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bitacademy.mysite.security.AuthInterceptor;
import com.bitacademy.mysite.security.AuthUserHandlerMethodArgumentResolver;
import com.bitacademy.mysite.security.LoginInterceptor;
import com.bitacademy.mysite.security.LogoutInterceptor;

public class SecurityConfig implements WebMvcConfigurer{
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
	
	// Interceptors
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
}
