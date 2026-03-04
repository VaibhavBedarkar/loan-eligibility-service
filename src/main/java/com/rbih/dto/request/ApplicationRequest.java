package com.rbih.dto.request;

import lombok.Data;

@Data
public class ApplicationRequest {

	private ApplicantRequest applicant;

	private LoanRequest loan;

	public ApplicantRequest getApplicant() {
		return applicant;
	}

	public void setApplicant(ApplicantRequest applicant) {
		this.applicant = applicant;
	}

	public LoanRequest getLoan() {
		return loan;
	}

	public void setLoan(LoanRequest loan) {
		this.loan = loan;
	}

}
