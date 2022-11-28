package com.bitacademy.mysite;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class MySiteApplication {
	
	@Controller
	public class HelloController {
		@RequestMapping("/hello")
		public String hello() {
			return "hello";
		}
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MySiteApplication.class, args);
	}
}
