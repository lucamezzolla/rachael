package com.rachael.api.wallet.exception;

public class ResourceNotFoundException extends RuntimeException {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = 173936570573472579L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
	
}