package com.caasa.comexsap.exportaciones;

import javax.servlet.Filter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.caasa.comexsap.exportaciones.spring.AppConfig;

@SpringBootApplication
@Import({AppConfig.class})
public class ComexsapExportacionesBackendApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ComexsapExportacionesBackendApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ComexsapExportacionesBackendApplication.class);
	}
	
	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET","POST","HEAD","PUT","DELETE","OPTIONS","TRACE","PATCH")
				.allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization");
			}
		};
	}
	
	@Bean
	public Filter getCharacterEncodingFilter() {
	    org.springframework.web.filter.CharacterEncodingFilter characterEncodingFilter = new org.springframework.web.filter.CharacterEncodingFilter();
	    characterEncodingFilter.setEncoding("UTF-8");
	    characterEncodingFilter.setForceEncoding(true);
	    return characterEncodingFilter;
	}

}
