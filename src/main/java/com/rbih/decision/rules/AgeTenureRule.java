package com.rbih.decision.rules;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.rbih.decision.EligibilityRule;
import com.rbih.domain.enums.RejectionReason;
import com.rbih.dto.request.ApplicationRequest;

@Component
public class AgeTenureRule implements EligibilityRule {

	@Override
	public RejectionReason evaluate(ApplicationRequest request, BigDecimal emi) {
		int age = request.getApplicant().getAge();

		int tenureYrs = request.getLoan().getTenureMonths() / 12;

		if (age + tenureYrs > 65) {
			return RejectionReason.AGE_TENURE_EXCEEDED;
		}
		return null;
	}

}
