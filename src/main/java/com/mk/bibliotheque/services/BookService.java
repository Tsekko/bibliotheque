package com.mk.bibliotheque.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mk.bibliotheque.exceptions.NotFoundException;
import com.mk.bibliotheque.interfaces.services.IBookService;
import com.mk.bibliotheque.models.Author;
import com.mk.bibliotheque.models.Book;
import com.mk.bibliotheque.models.Category;
import com.mk.bibliotheque.models.dtos.BookCreationDTO;
import com.mk.bibliotheque.repositories.AuthorRepository;
import com.mk.bibliotheque.repositories.BookRepository;
import com.mk.bibliotheque.repositories.CategoryRepository;

@Component
public class BookService implements IBookService {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private AuthorRepository authorRepository;
	
	@Override
	public Book getBookById(int id) {
		Book book = bookRepository.findById(id).orElseThrow();
		return book;
	}

	@Override
	public List<Book> getBooks() {
		List<Book> lstBooks = bookRepository.findAll();
		return lstBooks;
	}

	@Override
	public void createBook(BookCreationDTO book) {
		Book bookCreated = new Book(book.getTitle(), book.getDescription(), book.getPublishedDate());
		List<Category> bookCategories = new ArrayList<>();
		for (String categoryName : book.getCategoriesNames()) {
			Category category = categoryRepository.findByName(categoryName);
			if(null != category) {
				bookCategories.add(category);
			}
		}
		bookCreated.setLstCategories(bookCategories);
		// Retrieve index of first space in author name
		int firstSpace = book.getAuthor().indexOf(" ", 0);
		// Creating an author object using substring and the index of the first space
		Author author = new Author(book.getAuthor().substring(0, firstSpace), book.getAuthor().substring(firstSpace).trim());
		boolean authorExists = authorRepository.existsAuthorByFirstNameAndLastName(author.getFirstName(), author.getLastName());
		if (!authorExists) {
			authorRepository.saveAndFlush(author);
		} else {
			// Retrieve author if it already exists
			author = authorRepository.findAuthorByFirstNameAndLastName(book.getAuthor().substring(0, firstSpace), book.getAuthor().substring(firstSpace).trim());
		}
		bookCreated.setAuthor(author);
		bookRepository.saveAndFlush(bookCreated);
	}

	@Override
	public Book updateBook(int id, Book book) {
		Book existingBook = bookRepository.findById(id).orElseThrow();
		book.setId(existingBook.getId());
		bookRepository.saveAndFlush(book);
		return book;
	}

	@Override
	public void deleteBook(int id) throws Exception {
		if(bookRepository.existsById(id)) {
			bookRepository.deleteById(id);
		} else {
			throw new NoSuchElementException("Book not found with id: " + id);
		}
	}

}
