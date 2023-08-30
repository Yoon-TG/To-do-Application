package com.example.todo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //스프링 빈으로 등록 
public class WebMvcConfig implements WebMvcConfigurer{

	private final long MAX_AGE_SECS = 3600;
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //모든 경로에 대해
		.allowedOrigins("http://localhost:3000") //origin이 http~3000에 대해
		.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS") //이하의 메소드를 허용함
		.allowedHeaders("*") //모든 헤더와
		.allowCredentials(true) //인증에 관한 정보(credential)도 허용함
		.maxAge(MAX_AGE_SECS);
	}
	
}
