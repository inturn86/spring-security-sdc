package com.sdc.study.security.jwt.exception;

public class JwtRuntimeException extends SFPException {

    /**
	 *
	 */
	private static final long serialVersionUID = 9014162926305187233L;

	public JwtRuntimeException(){
        super("JWT exception 발생");
    }

    public JwtRuntimeException(Exception ex){
        super(ex);
    }
}
