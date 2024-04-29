package com.mk.bibliotheque.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "copies")
public class Copy {
	@Id
	@GeneratedValue
	private int id;
	@ManyToOne
	@JoinColumn(name = "book_id")
	private Book book;
	private int numberOfBorrows;
	@Enumerated(EnumType.STRING)
	private CopyState status;
	private boolean isAvailable = true;
	
	public Copy() {
		
	}
	
	public Copy(Book book) {
		this.book = book;
		this.numberOfBorrows = 0;
		this.status = CopyState.NEW;
	}

	public int getNumberOfBorrows() {
		return numberOfBorrows;
	}

	public void setNumberOfBorrows(int numberOfBorrows) {
		this.numberOfBorrows = numberOfBorrows;
	}

	public CopyState getStatus() {
		return status;
	}

	public void setStatus(CopyState status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public Book getBook() {
		return book;
	}
	
	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public void addOneBorrow() {
		this.numberOfBorrows++;
		modifyStatus(this.numberOfBorrows);
	}
	
	private void modifyStatus(int numberOfBorrows) {
		switch(numberOfBorrows) {
			case 2:
				this.status = CopyState.VERYGOOD;
				break;
			case 7:
				this.status = CopyState.GOOD;
				break;
			case 15:
				this.status = CopyState.OK;
				break;
			case 20:
				this.status = CopyState.BAD;
				break;
			default:
				break;
		}
	}
}
