package com.example.demo.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConifg implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //슬래쉬를 기준으로 (이미지 경로의 녀석들을 C:/upload/ 여기서 처리하겠다.
        registry.addResourceHandler("/IMG/**")
                //로컬경로 의미 file:/// 시작점 C:/upload/
                .addResourceLocations("file:///C:/upload/");
    }
}
