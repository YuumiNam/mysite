package com.bitacademy.mysite.config.web;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageResouceConfig {
	// Message Sourse
	@Bean
	public MessageSource resourceBundleMessageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("com/bitacademy/mysite/config/web/messages_ko");
		messageSource.setDefaultEncoding("utf-8");
		return messageSource;
	}
}
