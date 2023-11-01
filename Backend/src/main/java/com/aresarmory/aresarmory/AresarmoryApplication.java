package com.aresarmory.aresarmory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class AresarmoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(AresarmoryApplication.class, args);
	}
}
