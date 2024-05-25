//package com.example.sharing.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig {
//
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                registry.addMapping("/**") // Разрешаем CORS для всех путей
//                        .allowedOrigins("http://localhost:5174") // Разрешаем запросы с этого источника
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Разрешаем все основные HTTP методы
//                        .allowedHeaders("*") // Разрешаем все заголовки
//                        .allowCredentials(true); // Разрешаем отправку кукисов
//            }
//        };
//    }
//}
