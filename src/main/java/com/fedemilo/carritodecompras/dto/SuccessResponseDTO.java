package com.fedemilo.carritodecompras.dto;

public class SuccessResponseDTO {
    
    private String message;

    public SuccessResponseDTO(String message) {
        this.setMessage(message);
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
}