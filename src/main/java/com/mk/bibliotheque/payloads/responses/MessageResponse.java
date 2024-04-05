package com.mk.bibliotheque.payloads.responses;

public class MessageResponse {
	private String message;
	
	public MessageResponse(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return this.message;
	}
}
