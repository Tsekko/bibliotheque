package com.mk.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mk.bibliotheque.interfaces.repositories.BookRepositoryCustom;
import com.mk.bibliotheque.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom {
	
}
