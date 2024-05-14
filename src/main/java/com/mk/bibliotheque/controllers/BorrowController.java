package com.mk.bibliotheque.controllers;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.bibliotheque.exceptions.ClosedException;
import com.mk.bibliotheque.interfaces.services.IBorrowService;
import com.mk.bibliotheque.models.Borrow;
import com.mk.bibliotheque.models.dtos.BorrowCreationDTO;
import com.mk.bibliotheque.models.dtos.BorrowDTO;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
	@Autowired
	private IBorrowService borrowService;
	
	@GetMapping
	public ResponseEntity<List<Borrow>> getBorrows() throws Exception {
		List<Borrow> lstBorrows = borrowService.getBorrows();
		return new ResponseEntity<>(lstBorrows, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BorrowDTO> getBorrowById(@PathVariable int id) throws Exception {
		BorrowDTO borrow = borrowService.getBorrowById(id);
		return new ResponseEntity<>(borrow, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<String> createBorrow(@RequestBody BorrowCreationDTO borrow) throws Exception {
		borrowService.createBorrow(borrow);
		return new ResponseEntity<>("Borrow successfully registered", HttpStatus.CREATED);
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<List<BorrowDTO>> getBorrowByBookId(@PathVariable int id) throws Exception {
		List<BorrowDTO> borrow = borrowService.getBorrowsByBookId(id);
		return new ResponseEntity<>(borrow, HttpStatus.OK);
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<BorrowDTO> getBorrowByUserId(@PathVariable int id) throws Exception {
		BorrowDTO borrow = borrowService.getBorrowByUserId(id);
		return new ResponseEntity<>(borrow, HttpStatus.OK);
	}
	
	@PutMapping("/restitution/{borrowId}")
	public ResponseEntity<String> restitueBook(@PathVariable int borrowId) throws NoSuchElementException, ClosedException {
		borrowService.endBorrow(borrowId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
