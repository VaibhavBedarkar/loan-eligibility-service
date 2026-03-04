package com.rbih.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.rbih.domain.enums.EmploymentType;
import com.rbih.domain.enums.RiskBand;

@Service
public class InterestRateService {

	private static final BigDecimal BASE_RATE = BigDecimal.valueOf(12);

	public BigDecimal calculate(RiskBand riskBand, EmploymentType employmentType, BigDecimal loanAmt) {
		BigDecimal rate = BASE_RATE;

		if (riskBand == RiskBand.MEDIUM) {
			rate = rate.add(BigDecimal.valueOf(1.5));
		}

		if (riskBand == RiskBand.HIGH) {
			rate = rate.add(BigDecimal.valueOf(3));
		}

		if (riskBand == RiskBand.MEDIUM) {
			rate = rate.add(BigDecimal.valueOf(1.5));
		}

		if (employmentType == EmploymentType.SELF_EMPLOYED) {
			rate = rate.add(BigDecimal.ONE);
		}

		if (loanAmt.compareTo(BigDecimal.valueOf(10_00_000)) > 0) {
			rate = rate.add(BigDecimal.valueOf(0.5));
		}

		return rate;
	}

}
