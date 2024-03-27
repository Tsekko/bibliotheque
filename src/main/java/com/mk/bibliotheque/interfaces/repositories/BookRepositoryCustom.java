package com.mk.bibliotheque.interfaces.repositories;

import java.util.List;
import java.util.Map;

import com.mk.bibliotheque.models.Book;

public interface BookRepositoryCustom {
	List<Book> findBooksWithCustomQuery(Map<String, String> queryParams);
	List<Book> advancedSearchBooks(Map<String, String> queryParams);
}
