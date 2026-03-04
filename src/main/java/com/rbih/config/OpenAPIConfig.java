package com.rbih.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenAPIConfig {

	@Bean
	OpenAPI loanEvaluationServiceOpenAPI() {

		return new OpenAPI().info(new Info().title("Loan Evaluation Service API").version("1.0.0").description(
				"""
						REST API for evaluating loan applications and generating a single loan offer based on requested tenure. The service validates applicant details, applies eligibility rules, calculates EMI using financial formulas, classifies risk bands from credit score, and returns either an approved loan offer or rejection reasons."""));
	}

}
