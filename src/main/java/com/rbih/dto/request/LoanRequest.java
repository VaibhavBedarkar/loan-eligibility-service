package com.rbih.dto.request;

import java.math.BigDecimal;

import com.rbih.domain.enums.LoanPurpose;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanRequest {

	@NotNull(message = "Loan amount is required")
	@DecimalMin(value = "10000", message = "Loan amount must be atleast 10,000")
	@DecimalMax(value = "5000000", message = "Loan amount must not exceed 50,00,000")
	private BigDecimal amount;

	@Min(value = 6, message = "Loan tenure must be atleast for 6 months")
	@Min(value = 360, message = "Loan tenure must not exceed 360 months")
	private int tenureMonths;

	@NotNull(message = "Loan purpose is required")
	private LoanPurpose purpose;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public int getTenureMonths() {
		return tenureMonths;
	}

	public void setTenureMonths(int tenureMonths) {
		this.tenureMonths = tenureMonths;
	}

	public LoanPurpose getPurpose() {
		return purpose;
	}

	public void setPurpose(LoanPurpose purpose) {
		this.purpose = purpose;
	}

}
