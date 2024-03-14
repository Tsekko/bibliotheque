package com.mk.bibliotheque.interfaces.services;

import java.util.List;

import com.mk.bibliotheque.exceptions.InvalidFormException;
import com.mk.bibliotheque.models.Category;
import com.mk.bibliotheque.models.dtos.CategoryCreationDTO;

public interface ICategoryService {
	Category getCategoryById(int id);
	List<Category> getCategories();
	void createCategory(CategoryCreationDTO category) throws InvalidFormException;
	Category updateCategory(int id, Category category) throws InvalidFormException;
	void deleteCategory(int id) throws Exception;
}
