package com.mk.bibliotheque.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.mk.bibliotheque.models.Author;
import com.mk.bibliotheque.models.Book;
import com.mk.bibliotheque.models.dtos.BookCreationDTO;
import com.mk.bibliotheque.services.BookService;

@SpringBootTest
@AutoConfigureMockMvc
public class BookControllerTest {
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	BookService bookService;
	
	List<String> lstCat = new ArrayList<>();
	
	BookCreationDTO book;
	Book bookUpdated;
	
	@BeforeEach
	public void setup() {
		lstCat.add("Roman");
		book = new BookCreationDTO("Test", "John Doe", "lorem ipsum", 2024, lstCat);
		bookUpdated = new Book("TestUpdate", "lorem ipsum", 2024, new Author("Jane", "Doe"));
	}
	
	@Test
	@WithAnonymousUser
	public void getBooksWhenNotAuthenticated() throws Exception {
		mvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void getBooksAsUser() throws Exception {
		mvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(roles = {"USER", "ADMIN"})
	public void getBooksAsAdmin() throws Exception {
		mvc.perform(get("/books").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	// Access tests on GET /books/{id}
	
	@Test
	public void getBookByIdWhenNotAuthenticated() throws Exception {
		mvc.perform(get("/books/2")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void getBookByIdAsUser() throws Exception {
		mvc.perform(get("/books/5")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(roles = {"ADMIN"})
	public void getBookByIdAsAdmin() throws Exception {
		mvc.perform(get("/books/18")).andExpect(status().isOk());
	}
	
	// Access tests on POST /books
	
	@Test
	public void postBookWhenNotAuthenticated() throws Exception {
		mvc.perform(post("/books").content(objectMapper.writeValueAsString(book)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
		//assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}
	
	@WithMockUser
	@Test
	public void postBookAsUser() throws Exception {
		mvc.perform(post("/books").content(objectMapper.writeValueAsString(book)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}
	
	@WithMockUser(roles = {"USER", "ADMIN"})
	@Test
	public void postBookAsAdmin() throws Exception {
		mvc.perform(post("/books").content(objectMapper.writeValueAsString(book)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
	
	// Access tests on POST /books/{id}
	
	@Test
	public void updateBookWhenNotAuthenticated() throws Exception {
		mvc.perform(post("/books/3").content(objectMapper.writeValueAsString(bookUpdated)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser
	public void updateBookAsUser() throws Exception {
		mvc.perform(post("/books/3").content(objectMapper.writeValueAsString(bookUpdated)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(roles = {"ADMIN"})
	public void updateBookAsAdmin() throws Exception {
		mvc.perform(post("/books/3").content(objectMapper.writeValueAsString(bookUpdated)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	// Access tests on DELETE /books/{id}
	
	@Test
	public void deleteBooksWhenNotAuthenticated() throws Exception {
		mvc.perform(delete("/books/2")).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser
	public void deleteBooksAsUser() throws Exception {
		mvc.perform(delete("/books/2")).andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(roles = {"ADMIN"})
	public void deleteBooksAsAdmin() throws Exception {
		mvc.perform(delete("/books/2")).andExpect(status().isOk());
	}
	
	
}
