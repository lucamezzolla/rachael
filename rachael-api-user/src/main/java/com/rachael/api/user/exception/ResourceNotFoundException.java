package com.rachael.api.user.exception;

public class ResourceNotFoundException extends RuntimeException {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -8389509627622324908L;

	public ResourceNotFoundException(String message) {
        super(message);
    }
	
}