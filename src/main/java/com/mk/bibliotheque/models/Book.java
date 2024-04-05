package com.mk.bibliotheque.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "books")
public class Book {
	@Id
	@GeneratedValue
	private int id;
	private String title;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "author_id")
	private Author author;
	private String description;
	private Integer publishedDate;
	private Date createdAt = new Date();
	@ManyToMany
	@JoinTable(
			name = "book_category",
			joinColumns = @JoinColumn(name = "book_id"),
			inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> lstCategories;
	
	public Book() {}
	
	public Book(String title, String description, Integer publishedDate) {
		this.title = title;
		this.description = description;
		this.publishedDate = publishedDate;
	}
	
	public Book(String title, String description, Integer publishedDate, Author author) {
		this.title = title;
		this.description = description;
		this.publishedDate = publishedDate;
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

	public Integer getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(Integer publishedDate) {
		this.publishedDate = publishedDate;
	}

	public Set<Category> getLstCategories() {
		return lstCategories;
	}

	public void setLstCategories(Set<Category> lstCategories) {
		this.lstCategories = lstCategories;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
