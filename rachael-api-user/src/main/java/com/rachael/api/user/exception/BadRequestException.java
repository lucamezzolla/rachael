package com.rachael.api.user.exception;

public class BadRequestException extends RuntimeException {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -7657317028134801751L;

	public BadRequestException(String message) {
        super(message);
    }
    
}