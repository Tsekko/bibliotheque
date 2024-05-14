package com.mk.bibliotheque.interfaces.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.mk.bibliotheque.exceptions.ClosedException;
import com.mk.bibliotheque.models.Borrow;
import com.mk.bibliotheque.models.dtos.BorrowCreationDTO;
import com.mk.bibliotheque.models.dtos.BorrowDTO;

public interface IBorrowService {
	List<Borrow> getBorrows() throws Exception;
	BorrowDTO getBorrowById(int id) throws NoSuchElementException;
	void createBorrow(BorrowCreationDTO borrow) throws Exception;
	Borrow updateBorrow(Borrow borrow) throws Exception;
	List<BorrowDTO> getBorrowsByBookId(int id) throws Exception;
	BorrowDTO getBorrowByUserId(int id) throws NoSuchElementException;
	void endBorrow(int id) throws NoSuchElementException, ClosedException;
}
