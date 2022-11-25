package com.bitacademy.mysite.config.app;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {
	
	// Connection Pool DataSource
	@Bean
	public DataSource basicDataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/webdb?charset=utf8");
		dataSource.setUsername("webdb");
		dataSource.setPassword("webdb");
		
		return dataSource;
	}
}
