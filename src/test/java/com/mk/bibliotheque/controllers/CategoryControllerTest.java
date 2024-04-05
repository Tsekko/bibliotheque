package com.mk.bibliotheque.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mk.bibliotheque.models.Category;
import com.mk.bibliotheque.models.dtos.CategoryCreationDTO;
import com.mk.bibliotheque.services.CategoryService;

@SpringBootTest
@AutoConfigureMockMvc
public class CategoryControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper mapper;
	
	@MockBean
	private CategoryService categoryService;
	
	CategoryCreationDTO cat;
	Category category;
	List<Integer> ints = new ArrayList<>();
	
	@BeforeEach
	public void setup() {
		cat = new CategoryCreationDTO("Science-fiction");
		category = new Category("Fantasy");
		
	}
	
	// Access tests on GET /categories
	@Test
	public void getCategoriesWhenNotAuthenticated() throws Exception {
		mvc.perform(get("/categories")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void getCategoriesAsUser() throws Exception {
		mvc.perform(get("/categories")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void getCategoriesAsAdmin() throws Exception {
		mvc.perform(get("/categories")).andExpect(status().isOk());
	}
	
	// Access tests on GET /categories/{id}
	
	@ParameterizedTest
	@ValueSource(ints = {18, 37, 2})
	public void getCategoryByIdWhenNotAuthenticated(int arg) throws Exception {
		mvc.perform(get("/categories/"+arg)).andExpect(status().isOk());
	}
	
	@ParameterizedTest
	@WithMockUser
	@ValueSource(ints = {18, 37, 2})
	public void getCategoryByIdAsUser(int arg) throws Exception {
		mvc.perform(get("/categories/"+arg)).andExpect(status().isOk());
	}
	
	@ParameterizedTest
	@WithMockUser(roles = "ADMIN")
	@ValueSource(ints = {18, 37, 2})
	public void getCategoryByIdAsAdmin(int arg) throws Exception {
		mvc.perform(get("/categories/"+arg)).andExpect(status().isOk());
	}
	
	// Access tests on POST /categories
	
	@Test
	public void createCategoryWhenNotAuthenticated() throws Exception {
		mvc.perform(post("/categories").content(mapper.writeValueAsString(cat)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser
	public void createCategoryAsUser() throws Exception {
		mvc.perform(post("/categories").content(mapper.writeValueAsString(cat)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void createCategoryAsAdmin() throws Exception {
		mvc.perform(post("/categories").content(mapper.writeValueAsString(cat)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
	
	// Access tests on POST /categories/{id}
	
	@ParameterizedTest
	@ValueSource(ints = {18, 37, 2})
	public void updateCategoryWhenNotAuthenticated(int arg) throws Exception {
		mvc.perform(post("/categories/" + arg).content(mapper.writeValueAsString(category)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
	}
	
	@ParameterizedTest
	@WithMockUser
	@ValueSource(ints = {18, 37, 2})
	public void updateCategoryAsUser(int arg) throws Exception {
		mvc.perform(post("/categories/" + arg).content(mapper.writeValueAsString(category)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}
	
	@ParameterizedTest
	@WithMockUser(roles = "ADMIN")
	@ValueSource(ints = {18, 37, 2})
	public void updateCategoryAsAdmin(int arg) throws Exception {
		mvc.perform(post("/categories/" + arg).content(mapper.writeValueAsString(category)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	// Access tests on DELETE /categories/{id}
	
	@ParameterizedTest
	@ValueSource(ints = {55, 61, 4})
	public void deleteCategoryWhenNotAuthenticated(int arg) throws Exception {
		mvc.perform(delete("/categories/" + arg)).andExpect(status().isUnauthorized());
	}
	
	@ParameterizedTest
	@WithMockUser
	@ValueSource(ints = {55, 61, 4})
	public void deleteCategoryAsUser(int arg) throws Exception {
		mvc.perform(delete("/categories/" + arg)).andExpect(status().isForbidden());
	}
	
	@ParameterizedTest
	@WithMockUser(roles = "ADMIN")
	@ValueSource(ints = {55, 61, 4})
	public void deleteCategoryAsAdmin(int arg) throws Exception {
		mvc.perform(delete("/categories/" + arg)).andExpect(status().isOk());
	}
	
	
}
