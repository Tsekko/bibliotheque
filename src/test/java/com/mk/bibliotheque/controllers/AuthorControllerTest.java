package com.mk.bibliotheque.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mk.bibliotheque.models.Author;
import com.mk.bibliotheque.models.dtos.AuthorCreationDTO;
import com.mk.bibliotheque.services.AuthorService;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthorControllerTest {
	@Autowired
	MockMvc mvc;
	
	@Autowired
	ObjectMapper mapper;
	
	@MockBean
	AuthorService authorService;
	
	AuthorCreationDTO authorCreated = new AuthorCreationDTO("Jean", "De la fontaine");
	Author author = new Author("Isaac", "Asimov");
	
	// Access tests on GET /authors
	@Test
	public void getAuthorsWhenNotUnauthorized() throws Exception {
		mvc.perform(get("/authors")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void getAuthorsAsUser() throws Exception {
		mvc.perform(get("/authors")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void getAuthorsAsAdmin() throws Exception {
		mvc.perform(get("/authors")).andExpect(status().isOk());
	}
	
	// Access tests on GET /authors/{id}
	
	@Test
	public void getAuthorByIdWhenNotAuthenticated() throws Exception {
		mvc.perform(get("/authors/3")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser
	public void getAuthorByIdAsUser() throws Exception {
		mvc.perform(get("/authors/8")).andExpect(status().isOk());
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void getAuthorByIdAsAdmin() throws Exception {
		mvc.perform(get("/authors/7")).andExpect(status().isOk());
	}
	
	// Access tests on POST /authors
	
	@Test
	public void createAuthorWhenNotAuthenticated() throws Exception {
		mvc.perform(post("/authors").content(mapper.writeValueAsString(authorCreated)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser
	public void createAuthorAsUser() throws Exception {
		mvc.perform(post("/authors").content(mapper.writeValueAsString(authorCreated)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void createAuthorAsAdmin() throws Exception {
		mvc.perform(post("/authors").content(mapper.writeValueAsString(authorCreated)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
	}
	
	// Access tests on POST /authors/{id}
	
	@Test
	public void updateAuthorWhenNotAuthenticated() throws Exception {
		mvc.perform(post("/authors/6").content(mapper.writeValueAsString(author)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser
	public void updateAuthorAsUser() throws Exception {
		mvc.perform(post("/authors/9").content(mapper.writeValueAsString(author)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void updateAuthorAsAdmin() throws Exception {
		mvc.perform(post("/authors/16").content(mapper.writeValueAsString(author)).contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
	}
	
	// Access tests on DELETE /authors/{id}
	
	@Test
	public void deleteAuthorWhenNotAuthenticated() throws Exception {
		mvc.perform(delete("/authors/5")).andExpect(status().isUnauthorized());
	}
	
	@Test
	@WithMockUser
	public void deleteAuthorAsUser() throws Exception {
		mvc.perform(delete("/authors/78")).andExpect(status().isForbidden());
	}
	
	@Test
	@WithMockUser(roles = "ADMIN")
	public void deleteAuthorAsAdmin() throws Exception {
		mvc.perform(delete("/authors/35")).andExpect(status().isOk());
	}
}
