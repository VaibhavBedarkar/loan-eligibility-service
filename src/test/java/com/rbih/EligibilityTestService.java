package com.rbih;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.rbih.decision.LoanDecision;
import com.rbih.decision.LoanDecisionEngine;
import com.rbih.domain.enums.ApplicationStatus;
import com.rbih.domain.enums.EmploymentType;
import com.rbih.domain.enums.LoanPurpose;
import com.rbih.domain.enums.RejectionReason;
import com.rbih.domain.enums.RiskBand;
import com.rbih.dto.request.ApplicantRequest;
import com.rbih.dto.request.ApplicationRequest;
import com.rbih.dto.request.LoanRequest;
import com.rbih.dto.response.ApplicationResponse;
import com.rbih.repository.LoanApplicationRepository;
import com.rbih.service.LoanApplicationService;

@ExtendWith(MockitoExtension.class)
public class EligibilityTestService {

	@Mock
	private LoanDecisionEngine loanDecisionEngine;

	@Mock
	private LoanApplicationRepository loanApplicationRepository;

	@InjectMocks
	private LoanApplicationService service;

	@Test
	@DisplayName("should approve application when no rejection reasons")
	void process_whenEligible_shouldApprove() {

		ApplicationRequest request = buildRequest(30, 700, 36, new BigDecimal("75000"));

		LoanDecision decision = new LoanDecision();
		decision.setEmi(new BigDecimal("20000"));
		decision.setInterestRate(new BigDecimal("10"));
		decision.setRiskBand(RiskBand.LOW);
		decision.setRejectReasons(List.of());

		when(loanDecisionEngine.evaulate(request)).thenReturn(decision);

		ApplicationResponse response = service.process(request);

		assertThat(response.getStatus()).isEqualTo(ApplicationStatus.APPROVED);
		assertThat(response.getOffer()).isNotNull();
		assertThat(response.getOffer().getEmi()).isEqualByComparingTo("20000");
		assertThat(response.getOffer().getInterestRate()).isEqualByComparingTo("10");

	}

	@Test
	@DisplayName("should reject when credit score rule fails")
	void process_lowCreditScore_shouldReject() {

		ApplicationRequest request = buildRequest(30, 580, 36, new BigDecimal("75000"));

		LoanDecision decision = new LoanDecision();
		decision.setRejectReasons(List.of(RejectionReason.LOW_CREDIT_SCORE));

		when(loanDecisionEngine.evaulate(request)).thenReturn(decision);

		ApplicationResponse response = service.process(request);

		assertThat(response.getStatus()).isEqualTo(ApplicationStatus.REJECTED);
		assertThat(response.getRejectionReasons()).contains("LOW_CREDIT_SCORE");

	}

	@Test
	@DisplayName("should reject when age tenure rule fails")
	void process_ageTenureExceeded_shouldReject() {

		ApplicationRequest request = buildRequest(40, 700, 312, new BigDecimal("75000"));

		LoanDecision decision = new LoanDecision();
		decision.setRejectReasons(List.of(RejectionReason.AGE_TENURE_EXCEEDED));

		when(loanDecisionEngine.evaulate(request)).thenReturn(decision);

		ApplicationResponse response = service.process(request);

		assertThat(response.getStatus()).isEqualTo(ApplicationStatus.REJECTED);
		assertThat(response.getRejectionReasons()).contains("AGE_TENURE_EXCEEDED");

	}

	@Test
	@DisplayName("should reject when EMI exceeds 60 percent income")
	void process_emiTooHigh_shouldReject() {

		ApplicationRequest request = buildRequest(30, 700, 36, new BigDecimal("50000"));

		LoanDecision decision = new LoanDecision();
		decision.setRejectReasons(List.of(RejectionReason.EMI_EXCEEDS_60_PERCENT_MONTHLY_INCOME));

		when(loanDecisionEngine.evaulate(request)).thenReturn(decision);

		ApplicationResponse response = service.process(request);

		assertThat(response.getStatus()).isEqualTo(ApplicationStatus.REJECTED);
		assertThat(response.getRejectionReasons()).contains("EMI_EXCEEDS_60_PERCENT_MONTHLY_INCOME");

	}

	@Test
	@DisplayName("should collect multiple rejection reasons")
	void process_multipleRulesFail_shouldReturnAllReasons() {

		ApplicationRequest request = buildRequest(30, 500, 36, new BigDecimal("20000"));

		LoanDecision decision = new LoanDecision();
		decision.setRejectReasons(
				List.of(RejectionReason.LOW_CREDIT_SCORE, RejectionReason.EMI_EXCEEDS_60_PERCENT_MONTHLY_INCOME));
		when(loanDecisionEngine.evaulate(request)).thenReturn(decision);

		ApplicationResponse response = service.process(request);

		assertThat(response.getStatus()).isEqualTo(ApplicationStatus.REJECTED);
		assertThat(response.getRejectionReasons()).containsExactlyInAnyOrder("LOW_CREDIT_SCORE",
				"EMI_EXCEEDS_60_PERCENT_MONTHLY_INCOME");

	}

	private ApplicationRequest buildRequest(int age, int creditScore, int tenureMonths, BigDecimal monthlyIncome) {

		ApplicantRequest applicant = new ApplicantRequest();
		applicant.setAge(age);
		applicant.setCreditScore(creditScore);
		applicant.setMonthlyIncome(monthlyIncome);
		applicant.setEmploymentType(EmploymentType.SALARIED);
		applicant.setName("Test User");

		LoanRequest loan = new LoanRequest();
		loan.setAmount(new BigDecimal("500000"));
		loan.setTenureMonths(tenureMonths);
		loan.setPurpose(LoanPurpose.PERSONAL);

		ApplicationRequest request = new ApplicationRequest();
		request.setApplicant(applicant);
		request.setLoan(loan);

		return request;
	}
}
