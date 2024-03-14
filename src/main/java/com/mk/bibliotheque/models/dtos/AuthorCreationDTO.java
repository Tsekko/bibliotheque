package com.mk.bibliotheque.models.dtos;

public class AuthorCreationDTO {
	private String firstName;
	private String lastName;
	
	public AuthorCreationDTO(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
}
