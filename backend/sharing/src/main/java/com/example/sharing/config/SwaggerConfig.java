//package com.example.sharing.config;// Импорт необходимых библиотек и аннотаций
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration // Аннотация для обозначения класса конфигурации Spring
//@EnableSwagger2 // Аннотация для включения Swagger
//public class SwaggerConfig {
//
//    @Bean // Метод, создающий bean-компонент Docket для настройки Swagger
//    public Docket api() {
//        return new Docket(DocumentationType.SWAGGER_2) // Указываем тип документации
//                .select() // Начинаем настройку API
//                .apis(RequestHandlerSelectors.basePackage("com.example.sharing")) // Указываем пакет для сканирования контроллеров
//                .paths(PathSelectors.any()) // Указываем, что все пути должны быть задокументированы
//                .build(); // Строим и возвращаем Docket
//    }
//}
