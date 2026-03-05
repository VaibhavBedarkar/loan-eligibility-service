package com.rbih;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.rbih.service.EMICalculatorService;

@DisplayName("EMI Calculation Service")
public class EMIServiceTest {

	private EMICalculatorService service;

	@BeforeEach
	void setUp() {
		service = new EMICalculatorService();
	}

	@Test
	@DisplayName("should calculate correct EMI for standard salaried loan")
	void calculateEmi_standardCase() {
		BigDecimal principal = new BigDecimal("500000");
		BigDecimal annualRate = new BigDecimal("13.5");
		int tenure = 36;

		BigDecimal emi = service.calculate(principal, annualRate, tenure);

		assertThat(emi).isBetween(new BigDecimal("16900.00"), new BigDecimal("17000.00"));
	}

	@Test
	@DisplayName("should calculate correct EMI at base rate of 12%")
	void calculateEmi_baseRate() {
		BigDecimal principal = new BigDecimal("100000");
		BigDecimal annualRate = new BigDecimal("12.0");
		int tenure = 12;

		BigDecimal emi = service.calculate(principal, annualRate, tenure);

		assertThat(emi).isBetween(new BigDecimal("8800.00"), new BigDecimal("8900.00"));
	}

	@Test
	@DisplayName("should return scale of 2 for all results")
	void calculateEmi_scaleIsTwo() {
		BigDecimal emi = service.calculate(new BigDecimal("300000"), new BigDecimal("14.5"), 24);

		assertThat(emi.scale()).isEqualTo(2);
	}

	@Test
	@DisplayName("should handle large loan amounts correctly")
	void calculateEmi_largeLoan() {
		BigDecimal principal = new BigDecimal("5000000");
		BigDecimal annualRate = new BigDecimal("16.5");
		int tenure = 240;

		BigDecimal emi = service.calculate(principal, annualRate, tenure);

		assertThat(emi).isPositive();
		assertThat(emi.scale()).isEqualTo(2);
	}

	@Test
	@DisplayName("should handle minimum viable tenure of 6 months")
	void calculateEmi_minimumTenure() {
		BigDecimal principal = new BigDecimal("10000");
		BigDecimal annualRate = new BigDecimal("12.0");
		int tenure = 6;

		BigDecimal emi = service.calculate(principal, annualRate, tenure);

		assertThat(emi).isPositive();
		assertThat(emi.scale()).isEqualTo(2);
	}
}
