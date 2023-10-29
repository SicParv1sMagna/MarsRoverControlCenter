package com.example.roverbackcontrolcenter.configs;

import lombok.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // Укажите здесь домен вашего клиентского приложения
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
