package com.snyder.ldc.exception;

public class DuplicateResourceException extends LdcException {
	public DuplicateResourceException(String message) {
		super(message, "DUPLICATE_RESOURCE");
	}
}
