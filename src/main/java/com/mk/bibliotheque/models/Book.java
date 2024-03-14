package com.mk.bibliotheque.models;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {
	@Id
	@GeneratedValue
	private int id;
	private String title;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "owner_id")
	private Author author;
	private String description;
	private Date publishedDate;
	@ManyToMany
	@JoinTable(
			name = "book_category",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private List<Category> lstCategories;
	
	public Book() {}
	
	public Book(String title, String description, Date publishedIn) {
		this.title = title;
		this.description = description;
		this.publishedDate = publishedIn;
	}
	
	public Book(String title, String description, Date publishedIn, Author author) {
		this.title = title;
		this.description = description;
		this.publishedDate = publishedIn;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getPublishedIn() {
		return publishedDate;
	}

	public void setPublishedIn(Date publishedIn) {
		this.publishedDate = publishedIn;
	}

	public List<Category> getLstCategories() {
		return lstCategories;
	}

	public void setLstCategories(List<Category> lstCategories) {
		this.lstCategories = lstCategories;
	}
	
	
}
