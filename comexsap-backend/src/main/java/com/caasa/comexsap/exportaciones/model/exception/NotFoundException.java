package com.caasa.comexsap.exportaciones.model.exception;

public class NotFoundException extends Exception { 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
