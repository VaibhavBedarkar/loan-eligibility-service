package com.rbih.dto.request;

import java.math.BigDecimal;

import com.rbih.domain.enums.EmploymentType;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplicantRequest {

	@NotBlank(message = "Applicant name should not be blank")
	private String name;

	@Min(value = 21, message = "Age must be atleast 21")
	@Max(value = 60, message = "Age must not exceed 60")
	private int age;

	@NotNull(message = "Monthly Income is required")
	@DecimalMin(value = "0.01", message = "Monthly income must be greater than 0")
	private BigDecimal monthlyIncome;

	@NotNull(message = "Employment Type is required")
	private EmploymentType employmentType;

	@Min(value = 300, message = "Credit Score must be atleast 300")
	@Max(value = 900, message = "Credit Score must not exceed 900")
	private int creditScore;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(BigDecimal monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public EmploymentType getEmploymentType() {
		return employmentType;
	}

	public void setEmploymentType(EmploymentType employmentType) {
		this.employmentType = employmentType;
	}

	public int getCreditScore() {
		return creditScore;
	}

	public void setCreditScore(int creditScore) {
		this.creditScore = creditScore;
	}

}
