package com.example.roverbackcontrolcenter.configs;

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
        registry.addMapping("/api/**") // Указывайте здесь путь, который соответствует вашему API
                .allowedOrigins("http://localhost:8082")
                .allowCredentials(true);
    }
}
