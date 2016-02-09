package by.khrapovitsky.model;

import java.util.Date;

public class FileStatus {
	
	private int id;
	private int book;
	private String person;
	private Date dateUploaded;
	
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
	
	public String getPerson() {
		return person;
	}
	
	public void setPerson(String person) {
		this.person = person;
	}
	
	public Date getDateUploaded() {
		return dateUploaded;
	}
	
	public void setDateUploaded(Date dateUploaded) {
		this.dateUploaded = dateUploaded;
	}
	
}
