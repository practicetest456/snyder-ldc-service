package com.snyder.ldc.exception;

public class CapacityException extends LdcException {
	public CapacityException(String message) {
		super(message, "CAPACITY_EXCEEDED");
	}
}
