package com.mk.bibliotheque.interfaces.services;

import java.util.List;

import com.mk.bibliotheque.models.Book;
import com.mk.bibliotheque.models.dtos.BookCreationDTO;

public interface IBookService {
	Book getBookById(int id);
	List<Book> getBooks();
	void createBook(BookCreationDTO book);
	Book updateBook(int id, Book book);
	void deleteBook(int id) throws Exception;
}
