package com.rbih.service;

import com.rbih.dto.request.ApplicationRequest;
import com.rbih.dto.response.ApplicationResponse;

public interface LoanApplicationService {

	ApplicationResponse process(ApplicationRequest request);

}
