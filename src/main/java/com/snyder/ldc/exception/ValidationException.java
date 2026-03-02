package com.snyder.ldc.exception;

public class ValidationException extends LdcException {
	public ValidationException(String message) {
		super(message, "VALIDATION_ERROR");
	}
}
