package com.example.sharing;

//import com.jpomykala.springhoc.cors.EnableCORS;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.sharing"})
public class SharingApplication {

	public static void main(String[] args) {
		SpringApplication.run(SharingApplication.class, args);
	}
}
