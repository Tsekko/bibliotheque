package com.mk.bibliotheque.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Category {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	@ManyToMany(mappedBy = "lstCategories")
	@JsonIgnore
	private List<Book> lstBooks;
	
	public Category() {
		
	}
	
	public Category(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Book> getLstBooks() {
		return lstBooks;
	}

	public void setLstBooks(List<Book> lstBooks) {
		this.lstBooks = lstBooks;
	}
	
	
}
