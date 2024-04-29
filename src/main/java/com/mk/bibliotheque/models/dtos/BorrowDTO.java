package com.mk.bibliotheque.models.dtos;

import java.util.Date;

import com.mk.bibliotheque.models.Borrow;

public class BorrowDTO {
	private String titleBook;
	private String authorName;
	private String userEmail;
	private Date borrowedAt;
	private Date dueTo;
	private boolean hasBeenRestitued;
	
	public BorrowDTO(String titleBook, String authorName, String userEmail, Date borrowedAt, Date dueTo, boolean hasBeenRestitued) {
		this.titleBook = titleBook;
		this.authorName = authorName;
		this.userEmail = userEmail;
		this.borrowedAt = borrowedAt;
		this.dueTo = dueTo;
		this.hasBeenRestitued = hasBeenRestitued;
	}
	
	public BorrowDTO(Borrow borrow) {
		this.titleBook = borrow.getCopy().getBook().getTitle();
		this.authorName = borrow.getCopy().getBook().getAuthor().getAuthorName();
		this.userEmail = borrow.getUser().getEmail();
		this.borrowedAt = borrow.getCreatedAt();
		this.dueTo = borrow.getDueDate();
		this.hasBeenRestitued = borrow.getIsRestitued();
	}

	public String getTitleBook() {
		return titleBook;
	}

	public void setTitleBook(String titleBook) {
		this.titleBook = titleBook;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Date getBorrowedAt() {
		return borrowedAt;
	}

	public void setBorrowedAt(Date borrowedAt) {
		this.borrowedAt = borrowedAt;
	}

	public Date getDueTo() {
		return dueTo;
	}

	public void setDueTo(Date dueTo) {
		this.dueTo = dueTo;
	}

	public boolean isHasBeenRestitued() {
		return hasBeenRestitued;
	}

	public void setHasBeenRestitued(boolean hasBeenRestitued) {
		this.hasBeenRestitued = hasBeenRestitued;
	}
	
	
}
