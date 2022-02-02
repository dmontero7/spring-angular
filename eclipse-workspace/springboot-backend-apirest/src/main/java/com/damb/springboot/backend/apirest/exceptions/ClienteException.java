package com.damb.springboot.backend.apirest.exceptions;

public class ClienteException extends Exception {
	private String errorCode;

	public ClienteException(String errorCode, String errorMsg) {
		super(errorMsg);
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

}
