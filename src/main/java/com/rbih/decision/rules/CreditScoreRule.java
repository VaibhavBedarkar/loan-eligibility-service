package com.rbih.decision.rules;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.rbih.decision.EligibilityRule;
import com.rbih.domain.enums.RejectionReason;
import com.rbih.dto.request.ApplicationRequest;

@Component
public class CreditScoreRule implements EligibilityRule {

	@Override
	public RejectionReason evaluate(ApplicationRequest request, BigDecimal emi) {
		if (request.getApplicant().getCreditScore() < 600) {
			return RejectionReason.LOW_CREDIT_SCORE;
		}
		return null;
	}

}
