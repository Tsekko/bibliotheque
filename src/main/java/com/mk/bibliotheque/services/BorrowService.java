package com.mk.bibliotheque.services;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mk.bibliotheque.interfaces.services.IBorrowService;
import com.mk.bibliotheque.models.Borrow;
import com.mk.bibliotheque.models.Copy;
import com.mk.bibliotheque.models.User;
import com.mk.bibliotheque.models.dtos.BorrowCreationDTO;
import com.mk.bibliotheque.models.dtos.BorrowDTO;
import com.mk.bibliotheque.repositories.BorrowRepository;
import com.mk.bibliotheque.repositories.CopyRepository;
import com.mk.bibliotheque.repositories.UserRepository;

@Service
public class BorrowService implements IBorrowService {
	@Autowired
	private BorrowRepository borrowRepository;
	
	@Autowired
	private CopyRepository copyRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Borrow> getBorrows() throws Exception {
		return borrowRepository.findAll();
	}
	
	@Override
	public BorrowDTO getBorrowById(int id) throws NoSuchElementException {
		Borrow borrow = borrowRepository.findById(id).orElseThrow();
		return new BorrowDTO(borrow);
	}

	@Override
	public void createBorrow(BorrowCreationDTO borrow) throws Exception {		
		Copy copy = copyRepository.findByBookIdAndIsAvailableTrue(borrow.getBookId()).orElseThrow();
		
		User user = userRepository.findByEmail(borrow.getEmailUser()).orElseThrow();
		
		if (borrowRepository.existsByUserIdAndIsRestituedFalse(user.getId())) {
			throw new Exception("User cannot borrow a book");
		}
		
		Borrow borrowToCreate = new Borrow(copy, user);
		
		borrowRepository.saveAndFlush(borrowToCreate);
		
		copy.setAvailable(false);
		
		copyRepository.saveAndFlush(copy);
	}

	@Override
	public Borrow updateBorrow(Borrow borrow) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BorrowDTO> getBorrowsByBookId(int id) throws Exception {
		List<Borrow> borrows = borrowRepository.findByBookId(id);
		List<BorrowDTO> lstBorrows = new ArrayList<>();
		for (Borrow borrow : borrows) {
			lstBorrows.add(new BorrowDTO(borrow));
		}
		return lstBorrows;
	}

	@Override
	public BorrowDTO getBorrowByUserId(int id) throws NoSuchElementException {
		Borrow borrow = borrowRepository.findByUserId(id).orElseThrow();
		return new BorrowDTO(borrow);
	}

	@Override
	public void endBorrow(int id) throws NoSuchElementException {
		Borrow borrow = borrowRepository.findById(id).orElseThrow();
		borrow.hasBeenRestitued(true);
		borrowRepository.saveAndFlush(borrow);		
	}
}
