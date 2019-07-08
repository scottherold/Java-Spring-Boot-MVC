package com.sherold.mvc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sherold.mvc.models.Book;
import com.sherold.mvc.repositories.BookRepository;

// Annotation designates this class as a service
@Service
public class BookService {
	// <----- Attributes ----->
	// injects book repository
	private final BookRepository bookRepository;
	
	// <----- Constructors ----->
	// Dependency injection to make it available for access
	public BookService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}
	
	// <----- Methods ----->
	// These are methods called from your interface -- BookRepository
	
	// returns all books
	public List<Book> allBooks() {
		return bookRepository.findAll();
	}
	
	// creates a book
	// This is accessible due to extending the CRUD Repository into BookRepository
	public Book createBook(Book b) {
		return bookRepository.save(b);
	}
	
	// retrieves a book
	public Book findBook(Long id) {
		// Optional object is an object that essentially determines if an object exists or not
		// use an event handler for logic for if an Optional object is present
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.get();
		} else {
			return null;
		}
	}
	
	// updates a book
	public Book updateBook(Long id, String title, String desc, String lang, Integer numOfPages) {
		// Optional object is an object that essentially determines if an object exists or not
		// use an event handler for logic for if an Optional object is present
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			// Creates a book object to update using the optionalBook query
			Book updateBook = optionalBook.get();
			
			// Update Fields			
			updateBook.setTitle(title);
			updateBook.setDescription(desc);
			updateBook.setLanguage(lang);
			updateBook.setNumberOfPages(numOfPages);
			
			// Saves the bookObject
			return bookRepository.save(updateBook);
		} else {
			return null;
		}
	}
	
	// delete a book
	public void deleteBook(Long id) {
		// Optional object is an object that essentially determines if an object exists or not
		// use an event handler for logic for if an Optional object is present
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			bookRepository.deleteById(id);
		}
	}
}
