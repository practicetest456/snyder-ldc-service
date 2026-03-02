package com.snyder.ldc.exception;

public class DateValidationException extends LdcException {
	public DateValidationException(String message) {
		super(message, "DATE_VALIDATION_ERROR");
	}
}