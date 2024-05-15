package com.mk.bibliotheque.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class BorrowTest {
	@Test
	void restituteBorrowTest() {
		Copy copy = new Copy(new Book("Test", "Test book", 2024));
		User user = new User("Test user", "testemail@email.com", "1234");
		Borrow borrow = new Borrow(copy, user);
		borrow.restituteBorrow();
		assertEquals(1, borrow.getCopy().getNumberOfBorrows());
		assertEquals(true, borrow.getCopy().isAvailable());
		assertEquals(true, borrow.getIsRestituted());
	}
}
