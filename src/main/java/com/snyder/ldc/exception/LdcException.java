package com.snyder.ldc.exception;

public class LdcException extends RuntimeException {
	private String errorCode;

	public LdcException(String message) {
		super(message);
		this.errorCode = "GENERAL_ERROR";
	}

	public LdcException(String message, String errorCode) {
		super(message);
		this.errorCode = errorCode;
	}

	public LdcException(String message, Throwable cause) {
		super(message, cause);
		this.errorCode = "GENERAL_ERROR";
	}

	public String getErrorCode() {
		return errorCode;
	}
}
