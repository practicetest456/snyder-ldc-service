package com.snyder.ldc.exception;

public class ResourceNotFoundException extends LdcException {
	public ResourceNotFoundException(String message) {
		super(message, "RESOURCE_NOT_FOUND");
	}
}
