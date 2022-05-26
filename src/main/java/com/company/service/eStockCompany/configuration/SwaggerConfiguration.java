package com.company.service.eStockCompany.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfiguration {
	@Bean
	public Docket defaultApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.company.service.estockcompany"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder().title("Stock Application-Company Data ")
				.contact(new Contact("Ragavan R", "https://ragavfolio.herokuapp.com/", "ragavanravi65@gmail.com"))
				.description("Demonstration of Company Activities ").build();
		return apiInfo;
	}
}
