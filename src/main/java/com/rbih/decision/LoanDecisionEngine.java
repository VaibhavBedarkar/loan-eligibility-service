package com.rbih.decision;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Component;

import com.rbih.domain.enums.RejectionReason;
import com.rbih.domain.enums.RiskBand;
import com.rbih.dto.request.ApplicationRequest;
import com.rbih.service.EMICalculatorService;
import com.rbih.service.InterestRateService;
import com.rbih.service.RiskAssessmentService;

@Component
public class LoanDecisionEngine {

	private final RiskAssessmentService riskAssessmentService;
	private final InterestRateService interestRateService;
	private final EMICalculatorService eMICalculatorService;
	private final List<EligibilityRule> rules;

	public LoanDecisionEngine(RiskAssessmentService riskAssessmentService, InterestRateService interestRateService,
			EMICalculatorService eMICalculatorService, List<EligibilityRule> rules) {

		this.riskAssessmentService = riskAssessmentService;
		this.interestRateService = interestRateService;
		this.eMICalculatorService = eMICalculatorService;
		this.rules = rules;
	}

	public LoanDecision evaulate(ApplicationRequest request) {
		RiskBand riskBand = riskAssessmentService.classify(request.getApplicant().getCreditScore());

		BigDecimal rate = interestRateService.calculate(riskBand, request.getApplicant().getEmploymentType(),
				request.getLoan().getAmount());

		BigDecimal emi = eMICalculatorService.calculate(request.getLoan().getAmount(), rate,
				request.getLoan().getTenureMonths());

		List<RejectionReason> rejectionReasons = rules.stream().map(rule -> rule.evaluate(request, emi))
				.filter(r -> r != null).toList();

		return new LoanDecision(riskBand, rate, emi, rejectionReasons);
	}
}
