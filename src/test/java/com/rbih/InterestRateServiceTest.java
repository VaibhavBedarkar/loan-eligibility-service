package com.rbih;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.rbih.domain.enums.EmploymentType;
import com.rbih.domain.enums.RiskBand;
import com.rbih.service.InterestRateService;

@DisplayName("Interest Rate Service")
public class InterestRateServiceTest {

	private InterestRateService service;

    @BeforeEach
    void setUp() {
        service = new InterestRateService();
    }

    @ParameterizedTest(name = "riskBand={0}, employment={1}, amount={2} -> {3}%")
    @CsvSource({
            // Base(12) + Risk(0) + Employment(0) + Size(0) = 12.0
            "LOW,      SALARIED,      500000,  12.0",
            // Base(12) + Risk(1.5) + Employment(0) + Size(0) = 13.5
            "MEDIUM,   SALARIED,      500000,  13.5",
            // Base(12) + Risk(3) + Employment(0) + Size(0) = 15.0
            "HIGH,     SALARIED,      500000,  15.0",
            // Base(12) + Risk(0) + Employment(1) + Size(0) = 13.0
            "LOW,      SELF_EMPLOYED, 500000,  13.0",
            // Base(12) + Risk(1.5) + Employment(1) + Size(0) = 14.5
            "MEDIUM,   SELF_EMPLOYED, 500000,  14.5",
            // Base(12) + Risk(3) + Employment(1) + Size(0) = 16.0
            "HIGH,     SELF_EMPLOYED, 500000,  16.0",
            // Base(12) + Risk(0) + Employment(0) + Size(0.5) = 12.5  (loan > 10L)
            "LOW,      SALARIED,      1500000, 12.5",
            // Base(12) + Risk(3) + Employment(1) + Size(0.5) = 16.5
            "HIGH,     SELF_EMPLOYED, 2000000, 16.5"
    })
    @DisplayName("should compute correct final interest rate")
    void calculateFinalRate(RiskBand riskBand, EmploymentType employment, BigDecimal amount, BigDecimal expectedRate) {
        BigDecimal actualRate = service.calculate(riskBand, employment, amount);
        assertThat(actualRate).isEqualByComparingTo(expectedRate);
    }
}
