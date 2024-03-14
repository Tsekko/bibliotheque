package com.mk.bibliotheque.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mk.bibliotheque.exceptions.InvalidFormException;
import com.mk.bibliotheque.exceptions.NotFoundException;
import com.mk.bibliotheque.interfaces.services.ICategoryService;
import com.mk.bibliotheque.models.Category;
import com.mk.bibliotheque.models.dtos.CategoryCreationDTO;
import com.mk.bibliotheque.repositories.CategoryRepository;

@Component
public class CategoryService implements ICategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Category getCategoryById(int id) {
		Category category = categoryRepository.findById(id).orElseThrow();
		return category;
	}

	@Override
	public List<Category> getCategories() {
		List<Category> lstCategories = categoryRepository.findAll();
		return lstCategories;
	}

	@Override
	public void createCategory(CategoryCreationDTO category) throws InvalidFormException {
		Category categoryCreated = new Category(category.getName());
		try {
			validateForm(categoryCreated);
			categoryRepository.saveAndFlush(categoryCreated);
		} catch (InvalidFormException invalidFormException) {
			throw invalidFormException;
		}
	}

	@Override
	public Category updateCategory(int id, Category category) throws InvalidFormException {
		Category existingCategory = categoryRepository.findById(id).orElseThrow();
		try {
			validateForm(category);
			category.setId(existingCategory.getId());
			categoryRepository.saveAndFlush(category);
			return category;
		} catch (InvalidFormException invalidFormException) {
			throw invalidFormException;
		}
	}

	@Override
	public void deleteCategory(int id) throws Exception {
		if(categoryRepository.existsById(id)) {
			categoryRepository.deleteById(id);
		} else {
			throw new NotFoundException("Category not found with id: " + id);
		}
	}
	
	private void validateForm(Category category) throws InvalidFormException {
		if (null == category.getName() || category.getName().isBlank()) {
			throw new InvalidFormException("The category name is invalid");
		}
		if (category.getName().length() < 4) {
			throw new InvalidFormException("The category name must be longer than 3 characters");
		}
	}
}
