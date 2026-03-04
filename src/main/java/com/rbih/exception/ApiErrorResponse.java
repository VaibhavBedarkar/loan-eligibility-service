package com.rbih.exception;

import java.time.LocalDateTime;
import java.util.List;

public class ApiErrorResponse {

	private String errorCode;
	private String message;
	private List<String> details;
	private LocalDateTime timestamp;

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public ApiErrorResponse(String errorCode, String message, List<String> details, LocalDateTime timestamp) {
		super();
		this.errorCode = errorCode;
		this.message = message;
		this.details = details;
		this.timestamp = timestamp;
	}

}
