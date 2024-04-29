package com.mk.bibliotheque.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.bibliotheque.exceptions.NotFoundException;
import com.mk.bibliotheque.interfaces.services.IAuthorService;
import com.mk.bibliotheque.models.Author;
import com.mk.bibliotheque.models.dtos.AuthorCreationDTO;

@RestController
@RequestMapping("/authors")
public class AuthorController {
	@Autowired
	private IAuthorService authorService;
	
	@GetMapping
	public ResponseEntity<List<Author>> getAllAuthors() {
		List<Author> lstAuthors = authorService.getAuthors();
		return new ResponseEntity<>(lstAuthors, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Author> getAuthorById(@PathVariable int id) throws NoSuchElementException, Exception {
		Author author = authorService.getAuthorById(id);
		return new ResponseEntity<>(author, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createAuthor(@RequestBody AuthorCreationDTO authorToCreate) {
		Author newAuthor = new Author(authorToCreate.getFirstName(), authorToCreate.getLastName());
		authorService.createAuthor(newAuthor);
		return new ResponseEntity<>("Author created", HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}")
	public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author author) throws NoSuchElementException, Exception {
		try {
			Author authorUpdated = authorService.updateAuthor(id, author);
			return new ResponseEntity<>(authorUpdated, HttpStatus.OK);
		} catch (NotFoundException authorException) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception unknownException) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAuthor(@PathVariable int id) throws NoSuchElementException {
		authorService.deleteAuthor(id);
		return ResponseEntity.ok("Author deleted");
	}
}
