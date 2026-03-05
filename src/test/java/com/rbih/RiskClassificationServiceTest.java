package com.rbih;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.rbih.domain.enums.RiskBand;
import com.rbih.service.RiskAssessmentService;

@DisplayName("Risk Classification")
public class RiskClassificationServiceTest {
	
	private RiskAssessmentService service;
	
	@BeforeEach
	void setUp() {
		service = new RiskAssessmentService();
	}
	
	@ParameterizedTest(name = "Credit Score = {0} -> {1}")
	@CsvSource({
		"786,LOW",
		"750,LOW",
		"900, LOW",
		"650,MEDIUM",
		"700,MEDIUM",
		"749,MEDIUM",
		"649,HIGH",
		"629,HIGH"		
	})
	@DisplayName("Should return correct risk bank for credit score")
	void classify(int creditScore, RiskBand exceptedBand) {
		assertThat(service.classify(creditScore)).isEqualTo(exceptedBand);
		
	}
}
