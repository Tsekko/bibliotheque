package com.mk.bibliotheque.interfaces.services;

import java.util.List;

import com.mk.bibliotheque.models.Copy;

public interface ICopyService {
	List<Copy> getCopiesByBook(int bookId);
	Copy getCopyById(int id);
	void createCopy(int bookId);
	void deleteCopy(int id);
}
