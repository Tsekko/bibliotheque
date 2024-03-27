package com.mk.bibliotheque.interfaces.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.mk.bibliotheque.models.Book;
import com.mk.bibliotheque.models.dtos.BookCreationDTO;

public interface IBookService {
	Book getBookById(int id);
	List<Book> getBooks();
	void createBook(BookCreationDTO book);
	Book updateBook(int id, Book book);
	void deleteBook(int id) throws Exception;
	List<Book> searchBooks(Map<String, String> lstParams);
}
