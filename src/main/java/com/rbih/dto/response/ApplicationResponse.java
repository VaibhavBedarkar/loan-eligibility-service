package com.rbih.dto.response;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rbih.domain.enums.ApplicationStatus;
import com.rbih.domain.enums.RiskBand;

import lombok.Data;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Data
public class ApplicationResponse {

	private UUID applicationId;
	private ApplicationStatus status;
	private RiskBand riskBand;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private OfferResponse offer;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private List<String> rejectionReasons;

	public static ApplicationResponse approved(UUID applicationId, RiskBand riskBand, OfferResponse offer) {
		ApplicationResponse response = new ApplicationResponse();

		response.applicationId = applicationId;
		response.status = ApplicationStatus.APPROVED;
		response.riskBand = riskBand;
		response.offer = offer;

		return response;
	}

	public static ApplicationResponse rejected(UUID applicationId, List<String> rejectionReasons) {

		ApplicationResponse response = new ApplicationResponse();

		response.applicationId = applicationId;
		response.status = ApplicationStatus.REJECTED;
		response.riskBand = null;
		response.rejectionReasons = rejectionReasons;

		return response;
	}

	public UUID getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(UUID applicationId) {
		this.applicationId = applicationId;
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

	public OfferResponse getOffer() {
		return offer;
	}

	public void setOffer(OfferResponse offer) {
		this.offer = offer;
	}

	public List<String> getRejectionReasons() {
		return rejectionReasons;
	}

	public void setRejectionReasons(List<String> rejectionReasons) {
		this.rejectionReasons = rejectionReasons;
	}

}
