package com.mk.bibliotheque.repositories;

import java.util.List;

import com.mk.bibliotheque.interfaces.repositories.BorrowRepositoryCustom;
import com.mk.bibliotheque.models.Borrow;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class BorrowRepositoryImpl implements BorrowRepositoryCustom {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Borrow> findByBookId(int bookId) {
		String queryString = "SELECT * FROM borrows b INNER JOIN b.copy c INNER JOIN c.book bo WHERE bo.id EQUALS " + bookId;
		List<Borrow> lstBorrows = em.createQuery(queryString).getResultList();
		return lstBorrows;
	}

}
