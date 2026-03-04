package com.rbih.exception;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getField() + "-> " + e.getDefaultMessage()).toList();
		ApiErrorResponse resp = new ApiErrorResponse("VALIDATION_FAILED", "Invalid request Data", errors,
				LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resp);

	}

}
