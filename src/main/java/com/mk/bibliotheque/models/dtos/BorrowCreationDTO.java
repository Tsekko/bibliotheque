package com.mk.bibliotheque.models.dtos;

public class BorrowCreationDTO {
	private int bookId;
	private String emailUser;
	
	public BorrowCreationDTO() {
		
	}
	
	public BorrowCreationDTO(int bookId, String emailUser) {
		this.bookId = bookId;
		this.emailUser = emailUser;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getEmailUser() {
		return emailUser;
	}

	public void setEmailUser(String emailUser) {
		this.emailUser = emailUser;
	}
	
	
	
}
