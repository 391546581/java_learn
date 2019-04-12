package com.example.demo;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		SpringApplication application = new SpringApplicationBuilder().sources(DemoApplication.class)
				.addCommandLineProperties(true).registerShutdownHook(true).bannerMode(Banner.Mode.OFF)
				.build(args);
		application.setWebApplicationType(WebApplicationType.NONE);
		application.setAdditionalProfiles("default");
		application.run(args);
	}

}
