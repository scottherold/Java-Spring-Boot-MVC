package com.sherold.mvc.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.sherold.mvc.models.Book;

// Repository annotation designates this interface as a repository
@Repository

// CrudRepository allows for the application to perform CRUD operations to the database.
// The parameters are the Object and the 'type' of Id of the object (Long from Book).
public interface BookRepository extends CrudRepository<Book, Long>{
	// List of query options can be found on the Java Spring Data JPA documentation page.
	// (https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
	// Search for "query creation"
	// here are a few examples:
	
	// SELECT * query
	List<Book> findAll();
	
	// SELECT * WHERE description =
	List<Book> findByDescriptionContaining(String search);
	
	// COUNT * WHERE title =
	Long countByTitleContaining(String search);
	
	// DELETE * WHERE id =
	Long deleteById(String search);
}
