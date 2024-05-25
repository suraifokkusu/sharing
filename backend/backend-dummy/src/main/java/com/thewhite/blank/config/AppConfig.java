package com.thewhite.blank.config;

import org.hibernate.boot.model.naming.ImplicitNamingStrategy;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyComponentPathImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ImplicitNamingStrategy implicitNamingStrategy() {
        return new ImplicitNamingStrategyComponentPathImpl();
    }
}
