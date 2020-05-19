package com.fedemilo.carritodecompras.exceptions;

public class DataNotFoundException extends Exception {
    
    /**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String message;


    public DataNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}