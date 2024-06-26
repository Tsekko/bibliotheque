package com.mk.bibliotheque.controllers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.mk.bibliotheque.interfaces.services.IBookService;
import com.mk.bibliotheque.models.Book;
import com.mk.bibliotheque.models.dtos.BookCreationDTO;

@RestController
@RequestMapping("/books")
public class BookController {
	@Autowired
	private IBookService bookService;
	
	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks() {
		List<Book> lstBooks = bookService.getBooks();
		return new ResponseEntity<>(lstBooks, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable int id) throws NoSuchElementException, Exception {
		Book book = bookService.getBookById(id);
		return new ResponseEntity<>(book, HttpStatus.OK);
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> createBook(@RequestBody BookCreationDTO book) {
		bookService.createBook(book);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) throws NoSuchElementException, Exception {
		Book bookUpdated = bookService.updateBook(id, book);
		return new ResponseEntity<>(bookUpdated, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteBook(@PathVariable int id) throws NoSuchElementException, Exception {
		bookService.deleteBook(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Book>> searchBooks(@RequestParam Map<String, String> lstParams) {
		List<Book> lstBooks = bookService.searchBooks(lstParams);
		return new ResponseEntity<>(lstBooks, HttpStatus.OK);
	}
}
