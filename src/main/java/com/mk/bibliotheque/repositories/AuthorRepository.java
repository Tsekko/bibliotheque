package com.mk.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mk.bibliotheque.models.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	boolean existsAuthorByFirstNameAndLastName(String firstName, String lastName);
	Author findAuthorByFirstNameAndLastName(String firstName, String lastName);
}
