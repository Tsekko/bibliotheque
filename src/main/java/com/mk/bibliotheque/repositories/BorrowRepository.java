package com.mk.bibliotheque.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mk.bibliotheque.models.Borrow;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
	Optional<Borrow> findByBookId(int id);
	Optional<Borrow> findByUserId(int id);
}
