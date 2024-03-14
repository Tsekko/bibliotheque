package com.mk.bibliotheque.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mk.bibliotheque.exceptions.InvalidFormException;
import com.mk.bibliotheque.models.Category;
import com.mk.bibliotheque.models.dtos.CategoryCreationDTO;
import com.mk.bibliotheque.repositories.CategoryRepository;

class CategoryServiceTest {

	@Mock
	private CategoryRepository categoryRepository;
	
	@InjectMocks
	private CategoryService categoryService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
//	@Test
//	void createCategorySuccess() {
//		CategoryCreationDTO categoryToCreate = new CategoryCreationDTO("Science-fiction");
//		try {
//			categoryService.createCategory(categoryToCreate);
//		} catch (InvalidFormException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		verify(categoryRepository).saveAndFlush();
//	}
	
	@Test
	void createCategoryBlankTest() {
		CategoryCreationDTO categoryToCreate = new CategoryCreationDTO("");
		assertThrows(InvalidFormException.class, () -> categoryService.createCategory(categoryToCreate));
	}
	
	@Test
	void createCategoryLessThan4CharactersTest() {
		CategoryCreationDTO categoryToCreate = new CategoryCreationDTO("yes");
		assertThrows(InvalidFormException.class, () -> categoryService.createCategory(categoryToCreate));
	}

}
