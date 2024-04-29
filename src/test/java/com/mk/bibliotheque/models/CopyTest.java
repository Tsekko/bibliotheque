package com.mk.bibliotheque.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CopyTest {
	
	@Test
	void testModifyStatus() {
		Copy copy = new Copy(new Book("test", "test", 1952));
		copy.setNumberOfBorrows(1);
		copy.addOneBorrow();
		assertEquals(CopyState.VERYGOOD, copy.getStatus());
		assertEquals(2, copy.getNumberOfBorrows());
	}
	
	@Test
	void testIfStatusSwitchToGood() {
		Copy copy = new Copy(new Book("another book", "book", 1963));
		copy.setNumberOfBorrows(6);
		copy.addOneBorrow();
		assertEquals(CopyState.GOOD, copy.getStatus());
	}
	
	@Test
	void testIfStatusSwitchToOk() {
		Copy copy = new Copy(new Book("another book", "book", 1963));
		copy.setNumberOfBorrows(14);
		copy.addOneBorrow();
		assertEquals(CopyState.OK, copy.getStatus());
	}
	
	@Test
	void testIfStatusSwitchToBad() {
		Copy copy = new Copy(new Book("another book", "book", 1963));
		copy.setNumberOfBorrows(19);
		copy.addOneBorrow();
		assertEquals(CopyState.BAD, copy.getStatus());
	}
}
