package com.mk.bibliotheque.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mk.bibliotheque.interfaces.services.ICopyService;

@RestController
@RequestMapping("/copies")
public class CopyController {
	@Autowired
	private ICopyService copyService;
	
	@PostMapping("/book/{bookId}")
	public ResponseEntity<String> createCopy(@PathVariable int bookId) {
		copyService.createCopy(bookId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
