package com.cc.routefinder.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Configuration to enable the Swagger integration. Swagger documentation can be
 * accessed using http://localhost:8080/swagger-ui.html page after the
 * successful starting the application.
 * 
 * @author Vamshi
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("route-finder-api").select()
				.apis(RequestHandlerSelectors.basePackage("com.cc.routefinder")).paths(PathSelectors.any()).build()
				.useDefaultResponseMessages(false).apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Route Finder Rest API")
				.description("REST API documentation on finding the route between given two cities").version("1.0")
				.build();
	}
}
