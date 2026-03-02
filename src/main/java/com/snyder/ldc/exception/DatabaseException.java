package com.snyder.ldc.exception;

public class DatabaseException extends LdcException {
	public DatabaseException(String message, Throwable cause) {
		super(message, "DATABASE_ERROR");
	}
}
