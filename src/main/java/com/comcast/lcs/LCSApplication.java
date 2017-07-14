package com.comcast.lcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableWebMvc
public class LCSApplication extends SpringBootServletInitializer{
	public static void main(String[] args) {
		SpringApplication.run(LCSApplication.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("lcs").apiInfo(apiInfo()).select()
				.paths(PathSelectors.regex("/lcs/.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("LCS API").description("List of Rest API").license("License Version 0.0").licenseUrl("https://....").version("0.0").build();
	}
}
