package com.kbds.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
//        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173","http://192.168.61.252:5173") // 허용할 출처 설정
//                .allowedOrigins("http://192.168.61.252:5173") // 허용할 출처 설정
                .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용할 HTTP 메서드 설정
                .allowedHeaders("*") // 허용할 헤더 설정
                .allowCredentials(true); // 인증 정보 허용 설정
    }
}
