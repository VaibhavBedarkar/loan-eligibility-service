package com.rbih.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rbih.domain.enums.ApplicationStatus;
import com.rbih.dto.request.ApplicationRequest;
import com.rbih.dto.response.ApplicationResponse;
import com.rbih.service.LoanApplicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
public class LoanApplicationController {

	private final LoanApplicationService loanApplicationService;

	public LoanApplicationController(LoanApplicationService loanApplicationService) {
		this.loanApplicationService = loanApplicationService;
	}

	@PostMapping("/applications")
	@Operation(summary = "Evaluate loan application")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "Loan application approved"),
			@ApiResponse(responseCode = "400", description = "Loan application rejected") })
	ResponseEntity<ApplicationResponse> initiateApplication(@Valid @RequestBody ApplicationRequest request) {
		ApplicationResponse response = loanApplicationService.process(request);

		if (response.getStatus() == ApplicationStatus.APPROVED) {
			return ResponseEntity.ok(response);
		}

		return ResponseEntity.badRequest().body(response);
	}

}
