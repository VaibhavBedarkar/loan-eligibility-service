package com.rbih.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

@Service
public class EMICalculatorService {

	public BigDecimal calculate(BigDecimal principal, BigDecimal annualRate, int months) {
		BigDecimal montlyRate = annualRate.divide(BigDecimal.valueOf(12 * 100), 10, RoundingMode.HALF_UP);

		BigDecimal pow = montlyRate.add(BigDecimal.ONE).pow(months);

		BigDecimal numerator = principal.multiply(montlyRate).multiply(pow);

		BigDecimal denominator = pow.subtract(BigDecimal.ONE);

		return numerator.divide(denominator, 2, RoundingMode.HALF_UP);

	}
}
