package com.rbih.service;

import org.springframework.stereotype.Service;

import com.rbih.domain.enums.RiskBand;

@Service
public class RiskAssessmentService {

	public RiskBand classify(int creditScore) {

		if (creditScore >= 750) {
			return RiskBand.LOW;
		}

		if (creditScore >= 650) {
			return RiskBand.MEDIUM;
		}

		return RiskBand.HIGH;
	}

}
