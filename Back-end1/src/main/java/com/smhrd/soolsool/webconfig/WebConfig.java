package com.smhrd.soolsool.webconfig;

import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.CorsRegistry;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration

public class WebConfig implements WebMvcConfigurer {

	@Override

	public void addCorsMappings(CorsRegistry registry) {

		registry.addMapping("/**") // 모든 요청에 대해 CORS를 활성화합니다.
				.allowedOrigins("http://localhost:3000") // 허용되는 도메인을 설정합니다.
				.allowedMethods("GET", "POST", "PUT", "DELETE") // 허용되는 HTTP 메소드를 설정합니다.
				.allowCredentials(true) // 쿠키 및 기타 자격 증명을 허용합니다.
				.allowedHeaders("Content-Type") // 허용되는 헤더를 설정합니다.
				.maxAge(3600); // Preflight 요청의 캐시 시간을 1시간으로 설정합니다.

	}

}