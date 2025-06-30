package com.rinesarusinovci.online_quizzes_vue_back.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/images/profile/**")
                .addResourceLocations("file:///C:/Rinesa/online_quiz_springboot/uploads/profile-images/");
    }
}
