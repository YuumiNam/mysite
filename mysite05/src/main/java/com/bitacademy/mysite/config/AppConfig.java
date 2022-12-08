package com.bitacademy.mysite.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;

import com.bitacademy.mysite.config.app.DBConfig;
import com.bitacademy.mysite.config.app.MyBatisConfig;

@Configuration
@EnableAspectJAutoProxy // auto proxy : AOP weaving작업을 할 때 필요한 객체생성
@ComponentScan({"com.bitacademy.mysite.service", "com.bitacademy.mysite.repository", "com.bitacademy.mysite.aspect"})
@Import({DBConfig.class, MyBatisConfig.class})
public class AppConfig {
}
