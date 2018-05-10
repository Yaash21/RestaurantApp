package com.stackroute.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.stackroute.controller"))
				.paths(PathSelectors.regex("/api/v1/restaurant.*")).build().apiInfo(metaData());
	}

	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Spring Boot REST API", "Spring Boot REST API for Online Store", "1.0",
				"Terms of service", new Contact("Yaash Jain", null, "yaashjain21212@gmail.com"),
				"Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}

	// public Docket api() {
	// return new Docket(DocumentationType.SWAGGER_2).select()
	// .apis(RequestHandlerSelectors.basePackage("com.stackroute")).paths(PathSelectors.regex("/api/v1/*"))
	// .build();
	//
	// }

	// @Override
	// protected void addResourceHandlers(ResourceHandlerRegistry registry) {
	// registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
	//
	// registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
	// }
}
