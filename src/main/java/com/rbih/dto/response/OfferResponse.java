package com.rbih.dto.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OfferResponse {

	private BigDecimal interestRate;
	private int tenureMonths;
	private BigDecimal emi;
	private BigDecimal totalPayable;

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public int getTenureMonths() {
		return tenureMonths;
	}

	public void setTenureMonths(int tenureMonths) {
		this.tenureMonths = tenureMonths;
	}

	public BigDecimal getEmi() {
		return emi;
	}

	public void setEmi(BigDecimal emi) {
		this.emi = emi;
	}

	public BigDecimal getTotalPayable() {
		return totalPayable;
	}

	public void setTotalPayable(BigDecimal totalPayable) {
		this.totalPayable = totalPayable;
	}

}
