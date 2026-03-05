package com.rbih.decision;

import java.math.BigDecimal;
import java.util.List;

import com.rbih.domain.enums.RejectionReason;
import com.rbih.domain.enums.RiskBand;

import lombok.Builder;

@Builder
public class LoanDecision {

	private RiskBand riskBand;
	private BigDecimal interestRate;
	private BigDecimal emi;
	private List<RejectionReason> rejectReasons;

	public RiskBand getRiskBand() {
		return riskBand;
	}

	public void setRiskBand(RiskBand riskBand) {
		this.riskBand = riskBand;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public BigDecimal getEmi() {
		return emi;
	}

	public void setEmi(BigDecimal emi) {
		this.emi = emi;
	}

	public List<RejectionReason> getRejectReasons() {
		return rejectReasons;
	}

	public void setRejectReasons(List<RejectionReason> rejectReasons) {
		this.rejectReasons = rejectReasons;
	}

	public LoanDecision(RiskBand riskBand, BigDecimal interestRate, BigDecimal emi,
			List<RejectionReason> rejectReasons) {
		super();
		this.riskBand = riskBand;
		this.interestRate = interestRate;
		this.emi = emi;
		this.rejectReasons = rejectReasons;
	}

	public LoanDecision() {
		super();
	}

}
