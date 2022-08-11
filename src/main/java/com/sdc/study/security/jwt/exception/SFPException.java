package com.sdc.study.security.jwt.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class SFPException extends RuntimeException {

	private static final long serialVersionUID = 9042293579479320424L;
	private String errorMessage;

	public SFPException(Throwable err) {
		super(err.getMessage(), err);
	}

	public SFPException(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public SFPException(String errorMessage, Throwable err) {
		super(err.getMessage(), err);
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
}