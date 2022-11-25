package com.bitacademy.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.bitacademy.mysite.config.web.FileUploadConfig;
import com.bitacademy.mysite.config.web.MVCConfig;
import com.bitacademy.mysite.config.web.SecurityConfig;

@Configuration
@EnableAspectJAutoProxy // auto proxy : AOP weaving작업을 할 때 필요한 객체생성
@ComponentScan({"com.bitacademy.mysite.controller", "com.bitacademy.mysite.exception"})
@Import({MVCConfig.class, SecurityConfig.class, FileUploadConfig.class})
public class WebConfig {
}
