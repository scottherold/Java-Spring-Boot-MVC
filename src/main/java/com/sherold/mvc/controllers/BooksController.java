package com.sherold.mvc.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sherold.mvc.models.Book;
import com.sherold.mvc.services.BookService;

// Annoation for web-app controller
@Controller
public class BooksController {
	// <----- Dependency Injection (Attributes/Constructors) ----->
	// <----- Attributes ----->
	private final BookService bookService;
	
	// <----- Constructors ----->
	public BooksController(BookService bookService) {
		this.bookService = bookService;
	}
	
	// <----- Methods ----->
	// GET Route for All Books
	@RequestMapping("/books")	
	// Model used for injection into View
	public String index(Model model) {
		// Query list for all books
		List<Book> books = bookService.allBooks();
		
		// Add queried list to model for access in view
		model.addAttribute("books", books);
		
		return "/books/index.jsp";
	}
	
	// GET Route for New Book Form
	@RequestMapping("books/new")
	// Use of Model is to instantiate an empty new book to pass to our view.
	// This allows us to create a form that is bound to the book type, which allows us to validate information against the book type!
	public String newBook(@ModelAttribute("book") Book book) {
		return "/books/new.jsp";
	}
	
	// POST Route to CREATE a new book
	@RequestMapping(value="/books", method=RequestMethod.POST)
	// The @Valid annotation checks to see if the submitted object passed validation
	// @BindingResult must come immediately after the @Valid annotation parameter. This tells the application to check for errors!
	public String create(@Valid @ModelAttribute("book") Book book, BindingResult result) {
		
		// EventHandler for error checking
		if (result.hasErrors()) {
			return "books/new.jsp";
		} else {
			bookService.createBook(book);
			return "redirect:/books";
		}
	}
	
	// GET Route for show book by id
	@RequestMapping(value="books/{id}", method=RequestMethod.GET)
	// PathVariable for query, Model for binding the queried book to view
	public String show(@PathVariable("id") Long id, Model model) {
		// Queries the DB for the book
		Book book = bookService.findBook(id);
		
		// Binds book to model
		model.addAttribute("book", book);
		
		return "/books/show.jsp";
	}
	
	// GET Route for edit book form
	@RequestMapping("books/{id}/edit")
	// PathVariable for query, Model to bind form data to view
	public String edit(@PathVariable("id") Long id, Model model) {
		// Queries DB for book to bind to form
		Book book = bookService.findBook(id);
		
		// Binds book model to view through the model
		model.addAttribute("book", book);
		return "/books/edit.jsp";
	}
	
	// PUT Route to UPDATE book by id
	@RequestMapping(value="/books/{id}", method=RequestMethod.PUT)
	// The @Valid annotation checks to see if the submitted object passed validation
	// @BindingResult must come immediately after the @Valid annotation parameter. This tells the application to check for errors!
	public String update(
			@Valid 
			@ModelAttribute("book") Book book, 
			BindingResult result,
			@PathVariable("id") Long id,
			@RequestParam(value="title") String title, 
			@RequestParam(value="description") String desc, 
			@RequestParam(value="language") String lang, 
			@RequestParam(value="numberOfPages") Integer numOfPages
			) {
		// EventHandler for error checking
		if (result.hasErrors()) {
			return "books/edit.jsp";
		} else {
			bookService.updateBook(id, title, desc, lang, numOfPages);
			return "redirect:/books";
		}
	}
	
	// DELETE Route for book by id
	@RequestMapping(value="/books/{id}", method=RequestMethod.DELETE)
	public String destory(@PathVariable("id") Long id) {
		// Queries and deletes book from PathVariable
		bookService.deleteBook(id);;
		return "redirect:/books";
	}
}
