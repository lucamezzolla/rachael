package com.rachael.api.wallet.exception;

public class UnauthorizedException extends RuntimeException {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -2476168049280610655L;

	public UnauthorizedException(String message) {
        super(message);
    }
    
}