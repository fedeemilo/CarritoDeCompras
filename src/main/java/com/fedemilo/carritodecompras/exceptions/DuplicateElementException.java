package com.fedemilo.carritodecompras.exceptions;

public class DuplicateElementException extends Exception {
    
	private static final long serialVersionUID = 1L;
	private String message;

    public DuplicateElementException(String message) {
        super(message);
        this.message = message;
    }

}