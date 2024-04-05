package com.mk.bibliotheque.controllers;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

import com.mk.bibliotheque.exceptions.InvalidFormException;
import com.mk.bibliotheque.exceptions.NotFoundException;
import com.mk.bibliotheque.interfaces.services.ICategoryService;
import com.mk.bibliotheque.models.Category;
import com.mk.bibliotheque.models.dtos.CategoryCreationDTO;

@RestController
@RequestMapping("/categories")
public class CategoryController {
	@Autowired
	private ICategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<List<Category>> getAllCategories() {
		List<Category> lstCategories = categoryService.getCategories();
		return new ResponseEntity<>(lstCategories, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable int id) throws NoSuchElementException, Exception {
		Category category = categoryService.getCategoryById(id);
		return new ResponseEntity<>(category, HttpStatus.OK);	
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> createCategory(@RequestBody CategoryCreationDTO category) throws InvalidFormException, Exception {
			categoryService.createCategory(category);
			return new ResponseEntity<>("Category created", HttpStatus.CREATED);
	}
	
	@PostMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Category> updateCategory(@PathVariable int id, @RequestBody Category category) throws NoSuchElementException, InvalidFormException {
			Category categoryUpdated = categoryService.updateCategory(id, category);
			return new ResponseEntity<>(categoryUpdated, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> deleteCategory(@PathVariable int id) {
		try {
			categoryService.deleteCategory(id);
			return new ResponseEntity<>("Category deleted", HttpStatus.OK);
		} catch (NotFoundException notFound) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
