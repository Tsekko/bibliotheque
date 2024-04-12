package com.mk.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.bibliotheque.interfaces.services.IBorrowService;
import com.mk.bibliotheque.models.Book;
import com.mk.bibliotheque.models.Borrow;
import com.mk.bibliotheque.models.User;
import com.mk.bibliotheque.models.dtos.BorrowCreationDTO;
import com.mk.bibliotheque.models.dtos.BorrowDTO;
import com.mk.bibliotheque.repositories.BookRepository;
import com.mk.bibliotheque.repositories.BorrowRepository;
import com.mk.bibliotheque.repositories.UserRepository;

@Service
public class BorrowService implements IBorrowService {
	@Autowired
	private BorrowRepository borrowRepository;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Borrow> getBorrows() throws Exception {
		List<Borrow> lstBorrows = borrowRepository.findAll();
		return lstBorrows;
	}
	
	@Override
	public BorrowDTO getBorrowById(int id) throws Exception {
		Borrow borrow = borrowRepository.findById(id).orElseThrow();
		BorrowDTO borrowDisplayed = new BorrowDTO(borrow.getBook().getTitle(), 
				borrow.getBook().getAuthor().getFirstName() + " " + borrow.getBook().getAuthor().getLastName(), 
				borrow.getUser().getEmail(),
				borrow.getCreatedAt(),
				borrow.getDueDate(),
				borrow.getIsRestitued());
		return borrowDisplayed;
	}

	@Override
	public void createBorrow(BorrowCreationDTO borrow) throws Exception {		
		Book book = bookRepository.findById(borrow.getBookId()).orElseThrow();
		
		User user = userRepository.findByEmail(borrow.getEmailUser()).orElseThrow();
		
		Borrow borrowToCreate = new Borrow(book, user);
		
		borrowRepository.saveAndFlush(borrowToCreate);
	}

	@Override
	public Borrow updateBorrow(Borrow borrow) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BorrowDTO getBorrowByBookId(int id) throws Exception {
		Borrow borrow = borrowRepository.findByBookId(id).orElseThrow();
		BorrowDTO borrowDisplayed = new BorrowDTO(borrow.getBook().getTitle(), 
				borrow.getBook().getAuthor().getFirstName() + " " + borrow.getBook().getAuthor().getLastName(), 
				borrow.getUser().getEmail(),
				borrow.getCreatedAt(),
				borrow.getDueDate(),
				borrow.getIsRestitued());
		return borrowDisplayed;
	}

	@Override
	public BorrowDTO getBorrowByUserId(int id) throws Exception {
		Borrow borrow = borrowRepository.findByUserId(id).orElseThrow();
		BorrowDTO borrowDisplayed = new BorrowDTO(borrow.getBook().getTitle(), 
				borrow.getBook().getAuthor().getFirstName() + " " + borrow.getBook().getAuthor().getLastName(), 
				borrow.getUser().getEmail(),
				borrow.getCreatedAt(),
				borrow.getDueDate(),
				borrow.getIsRestitued());
		return borrowDisplayed;
	}
}
