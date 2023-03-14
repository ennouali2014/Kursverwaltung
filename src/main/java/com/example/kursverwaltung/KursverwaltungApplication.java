package com.example.kursverwaltung;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KursverwaltungApplication {

	public static void main(String[] args) {
			SpringApplication.run(KursverwaltungApplication.class, args);
			//BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			//System.out.println(encoder.encode("root"));

	}


	@Configuration
	public class WebConfig implements WebMvcConfigurer {
		@Override
		public void addResourceHandlers(ResourceHandlerRegistry registry) {
			registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");

		}

	}

}
