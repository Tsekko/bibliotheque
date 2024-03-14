package com.mk.bibliotheque.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mk.bibliotheque.interfaces.services.IAuthorService;
import com.mk.bibliotheque.models.Author;
import com.mk.bibliotheque.repositories.AuthorRepository;

@Component
public class AuthorService implements IAuthorService {
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public Author getAuthorById(int id) {
		Author author = authorRepository.findById(id).orElseThrow();
		return author;
	}

	@Override
	public List<Author> getAuthors() {
		List<Author> authors = authorRepository.findAll();
		return authors;
	}

	@Override
	public void createAuthor(Author author) {
		authorRepository.saveAndFlush(author);
	}

	@Override
	public Author updateAuthor(int id, Author author) throws Exception {
		Author existingAuthor = authorRepository.findById(id).orElseThrow();
		if (existingAuthor != null) {
			author.setId(existingAuthor.getId());
			authorRepository.saveAndFlush(author);
		}
		return author;
	}

	@Override
	public void deleteAuthor(int id) {
		boolean authorExists = authorRepository.existsById(id);
		if (authorExists) {
			authorRepository.deleteById(id);
		} else {
			throw new NoSuchElementException("Author not found with id: " + id);
		}
	}
	
	
}
