package com.rbih.service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rbih.decision.LoanDecision;
import com.rbih.decision.LoanDecisionEngine;
import com.rbih.domain.model.LoanApplication;
import com.rbih.dto.request.ApplicationRequest;
import com.rbih.dto.response.ApplicationResponse;
import com.rbih.dto.response.OfferResponse;
import com.rbih.repository.LoanApplicationRepository;

@Service
public class LoanApplicationService {

	@Autowired
	private LoanDecisionEngine loanDecisionEngine;

	@Autowired
	private LoanApplicationRepository loanApplicationRepository;

	public ApplicationResponse process(ApplicationRequest request) {

		LoanDecision decision = loanDecisionEngine.evaulate(request);

		ApplicationResponse response = null;

		UUID applicationid = UUID.randomUUID();

		if (!decision.getRejectReasons().isEmpty()) {
			response = ApplicationResponse.rejected(applicationid,
					decision.getRejectReasons().stream().map(Enum::name).toList());
		} else {
			OfferResponse offerResponse = new OfferResponse();
			offerResponse.setEmi(decision.getEmi());
			offerResponse.setInterestRate(decision.getInterestRate());
			offerResponse.setTenureMonths(request.getLoan().getTenureMonths());
			offerResponse.setTotalPayable(
					decision.getEmi().multiply(BigDecimal.valueOf(request.getLoan().getTenureMonths())));
			response = ApplicationResponse.approved(applicationid, decision.getRiskBand(), offerResponse);
		}

		loanApplicationRepository.save(toEntity(request, response));

		return response;
	}

	private LoanApplication toEntity(ApplicationRequest request, ApplicationResponse response) {
		LoanApplication application = new LoanApplication();

		application.setApplicantName(request.getApplicant().getName());
		application.setAge(request.getApplicant().getAge());
		application.setCreditScore(request.getApplicant().getCreditScore());
		application.setMontlyIncome(request.getApplicant().getMonthlyIncome());
		application.setEmploymentType(request.getApplicant().getEmploymentType());
		application.setLoanPurpose(request.getLoan().getPurpose());
		application.setLoanAmount(request.getLoan().getAmount());
		application.setStatus(response.getStatus());
		application.setRiskBand(response.getRiskBand());
		application.setTenureMonths(request.getLoan().getTenureMonths());
		if (response.getOffer() != null) {
			application.setOfferedInterestRate(response.getOffer().getInterestRate());
			application.setEmi(response.getOffer().getEmi());
			application.setTotalPayable(response.getOffer().getTotalPayable());

		}
		application.setRejectionReason(response.getRejectionReasons());
		return application;
	}

}
