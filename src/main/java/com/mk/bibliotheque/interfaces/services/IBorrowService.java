package com.mk.bibliotheque.interfaces.services;

import java.util.List;

import com.mk.bibliotheque.models.Borrow;
import com.mk.bibliotheque.models.dtos.BorrowCreationDTO;
import com.mk.bibliotheque.models.dtos.BorrowDTO;

public interface IBorrowService {
	List<Borrow> getBorrows() throws Exception;
	BorrowDTO getBorrowById(int id) throws Exception;
	void createBorrow(BorrowCreationDTO borrow) throws Exception;
	Borrow updateBorrow(Borrow borrow) throws Exception;
	BorrowDTO getBorrowByBookId(int id) throws Exception;
	BorrowDTO getBorrowByUserId(int id) throws Exception;
}
