package com.mk.bibliotheque.models;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "borrows")
public class Borrow {
	@Id
	@GeneratedValue
	private Integer id;
	@ManyToOne
	@JoinColumn(name="copy_id")
	private Copy copy;
	@ManyToOne
	@JoinColumn(name="customer_id")
	private User user;
	private Date createdAt = new Date();
	private Date dueDate;
	private Boolean isRestitued = false;
	
	public Borrow() {}
	
	public Borrow(Copy copy, User user) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_MONTH, 21);
		this.copy = copy;
		this.user = user;
		this.dueDate = calendar.getTime();
	}
	
	public Borrow(Copy copy, User user, Date dueDate) {
		this.copy = copy;
		this.user = user;
		this.dueDate = dueDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Boolean getIsRestitued() {
		return isRestitued;
	}

	public void hasBeenRestitued(Boolean restitued) {
		this.isRestitued = restitued;
	}
	
	
}
