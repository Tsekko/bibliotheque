package com.mk.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mk.bibliotheque.interfaces.services.ICopyService;
import com.mk.bibliotheque.models.Book;
import com.mk.bibliotheque.models.Copy;
import com.mk.bibliotheque.repositories.BookRepository;
import com.mk.bibliotheque.repositories.CopyRepository;

public class CopyService implements ICopyService {
	@Autowired
	CopyRepository copyRepository;
	
	@Autowired
	BookRepository bookRepository;

	@Override
	public List<Copy> getCopiesByBook(int bookId) {
		return copyRepository.findByBookId(bookId);
	}

	@Override
	public Copy getCopyById(int id) {
		return copyRepository.findById(id).orElseThrow();
	}

	@Override
	public void createCopy(int bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow();
		copyRepository.saveAndFlush(new Copy(book));
	}

	@Override
	public void deleteCopy(int id) {
		copyRepository.deleteById(id);
	}

}
