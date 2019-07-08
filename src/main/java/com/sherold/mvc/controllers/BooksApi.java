package com.sherold.mvc.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sherold.mvc.models.Book;
import com.sherold.mvc.services.BookService;

// Annotation for REST controller (API)

@RestController
public class BooksApi {
	// <----- Attributes ----->
	private final BookService bookService;

	// <----- Constructors ----->
	public BooksApi(BookService bookService) {
		this.bookService = bookService;
	}
	
	// <----- Methods ----->
	// routes
	// GET route for READING ALL books
	@RequestMapping("/api/books")
	public List<Book> index() {
		return bookService.allBooks();
	}
	
	// POST route to CREATE a new book	
	@RequestMapping(value="/api/books", method=RequestMethod.POST)
	public Book create(@RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
		Book book = new Book(title, desc, lang, numOfPages);
		return bookService.createBook(book);
	}
	
	// GET Route to READ one book
	@RequestMapping("/api/books/{id}")
	public Book show(@PathVariable("id") Long id) {
		Book book = bookService.findBook(id);
		return book;
	}
	
	// PUT route to UPDATE one book
	@RequestMapping(value="/api/books/{id}", method=RequestMethod.PUT)
	public Book update(@PathVariable("id") Long id, @RequestParam(value="title") String title, @RequestParam(value="description") String desc, @RequestParam(value="language") String lang, @RequestParam(value="pages") Integer numOfPages) {
		Book book = bookService.updateBook(id,  title,  desc,  lang,  numOfPages);
		return book;
	}
	
	// DELETE route to destroy book by id
	@RequestMapping(value="/api/books/{id}", method=RequestMethod.DELETE)
	public void destory(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
	}
}
