package com.rbih.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.rbih.domain.enums.ApplicationStatus;
import com.rbih.domain.enums.EmploymentType;
import com.rbih.domain.enums.LoanPurpose;
import com.rbih.domain.enums.RiskBand;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "loan_application")
@Data
public class LoanApplication {

	@Id
	private UUID id;

	private String applicantName;

	private int age;

	private BigDecimal montlyIncome;

	@Enumerated(EnumType.STRING)
	private EmploymentType employmentType;

	private int creditScore;

	private BigDecimal loanAmount;

	private int tenureMonths;

	@Enumerated(EnumType.STRING)
	private LoanPurpose loanPurpose;

	@Enumerated(EnumType.STRING)
	private ApplicationStatus status;

	@Enumerated(EnumType.STRING)
	private RiskBand riskBand;

	private BigDecimal offeredInterestRate;

	private BigDecimal emi;

	private BigDecimal totalPayable;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "rejection_reasons", joinColumns = @JoinColumn(name = "application_id"))
	@Column(name = "reason")
	private List<String> rejectionReason;

	private LocalDateTime createdAt;

	@PrePersist
	protected void onCreate() {

		if (id == null) {
			id = UUID.randomUUID();
		}
		createdAt = LocalDateTime.now();

	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getMontlyIncome() {
		return montlyIncome;
	}

	public void setMontlyIncome(BigDecimal montlyIncome) {
		this.montlyIncome = montlyIncome;
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

	public BigDecimal getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(BigDecimal loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getTenureMonths() {
		return tenureMonths;
	}

	public void setTenureMonths(int tenureMonths) {
		this.tenureMonths = tenureMonths;
	}

	public LoanPurpose getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(LoanPurpose loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public ApplicationStatus getStatus() {
		return status;
	}

	public void setStatus(ApplicationStatus status) {
		this.status = status;
	}

	public RiskBand getRiskBand() {
		return riskBand;
	}

	public void setRiskBand(RiskBand riskBand) {
		this.riskBand = riskBand;
	}

	public BigDecimal getOfferedInterestRate() {
		return offeredInterestRate;
	}

	public void setOfferedInterestRate(BigDecimal offeredInterestRate) {
		this.offeredInterestRate = offeredInterestRate;
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

	public List<String> getRejectionReason() {
		return rejectionReason;
	}

	public void setRejectionReason(List<String> rejectionReason) {
		this.rejectionReason = rejectionReason;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

}
