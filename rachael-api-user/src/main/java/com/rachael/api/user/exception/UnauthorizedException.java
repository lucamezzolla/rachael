package com.rachael.api.user.exception;

public class UnauthorizedException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -640658797384495328L;

	public UnauthorizedException(String message) {
        super(message);
    }
    
}