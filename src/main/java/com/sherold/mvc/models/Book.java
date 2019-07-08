package com.sherold.mvc.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

// Two annotations setup this class as a 'Domain Model'

// Entity means that this class is related to a database
@Entity

// This annoation allows for forward engineering into your DB. It creates the 'Books' table
@Table(name="books")
public class Book {
	// <----- Attributes ----->
	
	// Within the class, the fields are mapped with annotations
	// @GeneratedValue = auto generates the ID for the DB
	// @Size is a length validator
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 5, max = 200)
	private String title;
	
	@Size(min =5, max = 200)
	private String description;
	
	@Size(min = 3, max = 40)
	private String language;
	
	@Min(100)
	private Integer numberOfPages;
	
	// This will not allow the createdAt column to be updated after creation
	@Column(updatable=false)
	private Date createdAt;
	private Date updatedAt;
	
	// <----- Constructors ----->
	public Book() {
		
	}
	
	public Book(String title, String desc, String lang, Integer pages) {
		this.title = title;
		this.description = desc;
		this.language = lang;
		this.numberOfPages = pages;
	}
	
	// <----- Getters/Setters ----->
	// id
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	// title
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	// description
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	// language
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	// numberOfPages
	public Integer getNumberOfPages() {
		return numberOfPages;
	}

	public void setNumberOfPages(Integer numberOfPages) {
		this.numberOfPages = numberOfPages;
	}

	// createdAt
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	// updatedAt
	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	// <----- Methods ----->

	// @PrePersist means that this method is done before the object is created
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	// @PreUpdate means that this method is done every time the object is updated
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
}
