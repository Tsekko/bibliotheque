package com.mk.bibliotheque.interfaces.repositories;

import java.util.List;

import com.mk.bibliotheque.models.Borrow;

public interface BorrowRepositoryCustom {
	List<Borrow> findByBookId(int bookId);
}
