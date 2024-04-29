package com.mk.bibliotheque.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mk.bibliotheque.models.Copy;

@Repository
public interface CopyRepository extends JpaRepository<Copy, Integer> {
	List<Copy> findByBookId(int bookId);
	Optional<Copy> findByBookIdAndIsAvailableTrue(int bookId);
}
