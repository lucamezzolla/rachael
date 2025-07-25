package com.rachael.api.wallet.exception;

public class BadRequestException extends RuntimeException {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 6194453494285097835L;

	public BadRequestException(String message) {
        super(message);
    }
    
}