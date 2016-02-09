package by.khrapovitsky.model;

import java.util.Date;

public class BookMarks {
	
	private int id;
	private int book;
	private String login;
	private Date lastDateReading;
	private int page;
	private Books books;
	
	public Books getBooks() {
		return books;
	}
	public void setBooks(Books books) {
		this.books = books;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBook() {
		return book;
	}
	public void setBook(int book) {
		this.book = book;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public Date getLastDateReading() {
		return lastDateReading;
	}
	public void setLastDateReading(Date lastDateReading) {
		this.lastDateReading = lastDateReading;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
}
