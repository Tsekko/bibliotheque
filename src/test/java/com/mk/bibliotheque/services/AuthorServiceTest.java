package com.mk.bibliotheque.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mk.bibliotheque.models.Author;
import com.mk.bibliotheque.repositories.AuthorRepository;

class AuthorServiceTest {
	
	@Mock
	private AuthorRepository authorRepository;
	
	@InjectMocks
	private AuthorService authorService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
		
	@Test
	void createAuthorSuccessTest() {
		Author newAuthor = new Author("Jean", "De la Fontaine");
		authorService.createAuthor(newAuthor);
		verify(authorRepository).saveAndFlush(newAuthor);
	}

	@Test
	void getAuthorSuccessTest() {
		Author authorToRetrieve = new Author(1, "Bernard", "Weber");
		when(authorRepository.findById(anyInt())).thenReturn(Optional.of(authorToRetrieve));
		Author authorRetrieved = authorService.getAuthorById(1);
		verify(authorRepository).findById(1);
		assertSame(authorRetrieved, authorToRetrieve);
	}
	
	@Test
	void getAllAuthorsTest() {
		List<Author> lstAuthors = new ArrayList<Author>() {{
			add(new Author(1, "Jean", "De la Fontaine"));
			add(new Author(2, "Bernard", "Weber"));
		}};
		when(authorRepository.findAll()).thenReturn(lstAuthors);
		List<Author> lstAuthorRetrieved = authorService.getAuthors();
		verify(authorRepository).findAll();
		assertEquals(2, lstAuthorRetrieved.size());
	}
}
