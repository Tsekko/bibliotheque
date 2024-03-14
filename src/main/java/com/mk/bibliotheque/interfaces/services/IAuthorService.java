package com.mk.bibliotheque.interfaces.services;

import java.util.List;

import com.mk.bibliotheque.models.Author;

public interface IAuthorService {
	Author getAuthorById(int id);
	List<Author> getAuthors();
	void createAuthor(Author author);
	Author updateAuthor(int id, Author author) throws Exception;
	void deleteAuthor(int id);
}
