package com.company.service.eStockCompany;

import com.company.service.eStockCompany.Repository.mongoDB.CompanyMongoRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@SpringBootApplication
@EnableEurekaClient
@EnableMongoRepositories //(basePackageClasses = CompanyMongoRepository.class)
//@EnableResourceServer
public class EStockCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EStockCompanyApplication.class, args);
	}


	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder
				.setConnectTimeout(Duration.ofMillis(10000))
				.setReadTimeout(Duration.ofMillis(10000))
				.build();
	}

}
