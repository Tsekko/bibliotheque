package com.mk.bibliotheque.models.dtos;

import java.util.Date;
import java.util.List;

public class BookCreationDTO {
	private String title;
	private String author;
	private String description;
	private Integer publishedDate;
	private List<String> categoriesNames;
	
	public BookCreationDTO() {
		
	}
	
	public BookCreationDTO(String title, String author, String description, Integer publishedDate, List<String> categoriesNames) {
		this.title = title;
		this.author = author;
		this.description = description;
		this.publishedDate = publishedDate;
		this.categoriesNames = categoriesNames;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getAuthor() {
		return author;
	}
	
	public String getDescription() {
		return description;
	}
	
	public Integer getPublishedDate() {
		return publishedDate;
	}
	
	public List<String> getCategoriesNames() {
		return categoriesNames;
	}
}
