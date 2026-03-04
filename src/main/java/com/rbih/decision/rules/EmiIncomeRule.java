package com.rbih.decision.rules;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.rbih.decision.EligibilityRule;
import com.rbih.domain.enums.RejectionReason;
import com.rbih.dto.request.ApplicationRequest;

@Component
public class EmiIncomeRule implements EligibilityRule {

	@Override
	public RejectionReason evaluate(ApplicationRequest request, BigDecimal emi) {
		BigDecimal income = request.getApplicant().getMonthlyIncome();

		if (emi.compareTo(income.multiply(BigDecimal.valueOf(0.60))) > 0) {
			return RejectionReason.EMI_EXCEEDS_60_PERCENT_MONTHLY_INCOME;
		}

		if (emi.compareTo(income.multiply(BigDecimal.valueOf(0.50))) > 0) {
			return RejectionReason.EMI_EXCEEDS_50_PERCENT_MONTHLY_INCOME;
		}
		return null;
	}
}
