package com.rbih.decision;

import java.math.BigDecimal;

import com.rbih.domain.enums.RejectionReason;
import com.rbih.dto.request.ApplicationRequest;

public interface EligibilityRule {

	RejectionReason evaluate(ApplicationRequest request, BigDecimal emi);

}
