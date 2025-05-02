package com.study.week6.common.config;

import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    // API 기본 정보
    private Info apiInfo() {
        return new Info()
                .title("📎 Spring Study API Document")
                .description("스프링 스터디 6주차 - [RESTful 아키텍처 원칙] 을 공부한 코드를 설명하는 문서입니다.")
                .contact(new Contact().email("yeonguo95@gmail.com"))
                .version("1.0.0");
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }
}