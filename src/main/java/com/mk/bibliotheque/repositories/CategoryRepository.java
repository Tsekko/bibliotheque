package com.mk.bibliotheque.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mk.bibliotheque.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	Category findByName(String name);
}
