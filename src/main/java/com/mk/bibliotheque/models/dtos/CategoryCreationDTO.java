package com.mk.bibliotheque.models.dtos;

public class CategoryCreationDTO {
	private String name;
	
	public CategoryCreationDTO() {}
	
	public CategoryCreationDTO(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
}
