package com.mk.bibliotheque.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.mk.bibliotheque.interfaces.repositories.BookRepositoryCustom;
import com.mk.bibliotheque.models.Author;
import com.mk.bibliotheque.models.Book;
import com.mk.bibliotheque.models.Category;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

@Repository
public class BookRepositoryImpl implements BookRepositoryCustom {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Book> findBooksWithCustomQuery(Map<String, String> queryParams) {
		CriteriaBuilder queryBuild = em.getCriteriaBuilder();
		CriteriaQuery<Book> query = queryBuild.createQuery(Book.class);
		Root<Book> root = query.from(Book.class);
		List<Predicate> predicates = new ArrayList<>();
		for(String key : queryParams.keySet()) {
			switch (key) {
			case "title":
				predicates.add(queryBuild.like(root.get("title"), "%"+queryParams.get(key)+"%"));
				break;
			case "categoryName":
				Subquery<Integer> subqueryCategory = query.subquery(Integer.class);
				Root<Book> rootBook = subqueryCategory.from(Book.class);
				Join<Category, Book> joinCategory = rootBook.join("lstCategories");
				// Subquery retrieves all book ids with a category corresponding to the one passed
				subqueryCategory.select(rootBook.get("id")).where(queryBuild.equal(joinCategory.get("name"), queryParams.get(key)));
				predicates.add(queryBuild.in(root.get("id")).value(subqueryCategory));
				break;
			case "author":
				String author = queryParams.get(key);
				Subquery<Integer> subqueryAuthor = query.subquery(Integer.class);
				Root<Book> anotherRootBook = subqueryAuthor.from(Book.class);
				Join<Author, Book> joinAuthor = anotherRootBook.join("author");
				if (author.contains(" ")) {
					// Dividing string in two parts
					String firstPart = author.substring(0, author.indexOf(" "));
					String secondPart = author.substring(author.indexOf(" ")).trim();
					Predicate searchFirstPartOnLastName = queryBuild.like(joinAuthor.get("lastName"), "%"+firstPart+"%");
					Predicate searchSecondPartOnLastName = queryBuild.like(joinAuthor.get("lastName"), "%"+secondPart+"%");
					Predicate searchFirstPartOnFirstName = queryBuild.like(joinAuthor.get("firstName"), "%"+firstPart+"%");
					Predicate searchSecondPartOnFirstName = queryBuild.like(joinAuthor.get("firstName"), "%"+secondPart+"%");
					subqueryAuthor.select(anotherRootBook.get("id"))
						.where(queryBuild.and(
								queryBuild.or(searchFirstPartOnLastName, 
										searchFirstPartOnFirstName),
								queryBuild.or(searchSecondPartOnLastName, 
										searchSecondPartOnFirstName)));
				} else {
					Predicate searchFirstName = queryBuild.like(joinAuthor.get("firstName"), "%"+author+"%");
					Predicate searchLastName = queryBuild.like(joinAuthor.get("lastName"), "%"+author+"%");
					subqueryAuthor.select(anotherRootBook.get("id"))
						.where(queryBuild.or(searchFirstName, searchLastName));
				}
				predicates.add(queryBuild.in(root.get("id")).value(subqueryAuthor));
				break;
			case "publishedDate":
				predicates.add(queryBuild.equal(root.get("publishedDate"), Integer.parseInt(queryParams.get(key))));
				break;
			default:
				throw new IllegalArgumentException("Unexpected value: " + key);
			}
		}
		Predicate[] predicateArray = predicates.toArray(new Predicate[predicates.size()]);
		query.select(root).where(predicateArray);
		Query finalQuery = em.createQuery(query);
		List<Book> results = finalQuery.getResultList();
		return results;
	}
	
	@Override
	public List<Book> advancedSearchBooks(Map<String, String> queryParams) {
		String queryString = "SELECT DISTINCT b, a FROM Book b "
				+ "JOIN b.author a "
				+ "JOIN fetch b.lstCategories c";
		if (queryParams.size() > 0) {
			queryString = queryString.concat(" WHERE ");
			for (String key : queryParams.keySet()) {
				switch (key) {
					case "title":
						queryString = queryString.concat("b.title LIKE '%" + queryParams.get(key) + "%' AND ");
						break;
					case "publishedDate":
						queryString = queryString.concat("b.publishedDate = " + Integer.parseInt(queryParams.get(key)) + " AND ");
						break;
					case "author":
						String author = queryParams.get(key);
						if (author.contains(" ")) {
							String firstPart = author.substring(0, author.indexOf(" "));
							String secondPart = author.substring(author.indexOf(" ")).trim();
							queryString = queryString.concat("(a.firstName LIKE '%" + firstPart +"%' OR a.firstName LIKE '%" + secondPart + "%') AND "
									+ "(a.lastName LIKE '%" + firstPart + "%' OR a.lastName LIKE '%" + secondPart + "%') AND ");
						} else {
							queryString = queryString.concat("(a.firstName LIKE '%" + author + "%' OR a.lastName LIKE '%" + author + "%') AND ");
						}
						break;
					case "categoryName":
						queryString = queryString.concat("c.name = '" + queryParams.get(key) + "' AND ");
						break;
				}
			}
		}
		queryString = queryString.substring(0, queryString.length()-5);
		List<Book> results = em.createQuery(queryString).getResultList();
		return results;
	}
}
