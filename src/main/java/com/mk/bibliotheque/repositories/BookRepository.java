package com.mk.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mk.bibliotheque.models.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
