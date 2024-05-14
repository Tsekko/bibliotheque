package com.mk.bibliotheque.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mk.bibliotheque.interfaces.repositories.BorrowRepositoryCustom;
import com.mk.bibliotheque.models.Borrow;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow, Integer>, BorrowRepositoryCustom {
	Optional<Borrow> findByCopyId(int id);
	Optional<Borrow> findByUserId(int id);
	Boolean existsByUserIdAndIsRestitutedFalse(long userId);
}
