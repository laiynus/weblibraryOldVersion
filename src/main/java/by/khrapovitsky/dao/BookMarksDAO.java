package by.khrapovitsky.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import by.khrapovitsky.model.BookMarks;

interface BookMarksDAO {

	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the BookMarks table.
	    */
	   public void create(Integer book, String login, Date lastDateReading,Integer page);
	   /** 
	    * This is the method to be used to list down
	    * a record from the BookMarks table corresponding
	    * to a passed bookmark id.
	    */
	   public BookMarks getBookMark(Integer id);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the BookMarks table.
	    */
	   public List<BookMarks> listBookMarks();
	   /** 
	    * This is the method to be used to delete
	    * a record from the BookMarks table corresponding
	    * to a passed bookmark id.
	    */
	   public void delete(Integer id);
	   /** 
	    * This is the method to be used to update
	    * a record into the BookMark table.
	    */
	   public void update(Integer id, Integer book ,String login, Date lastDateReading,Integer page);
	   /** 
	    * This is the method to be used to list down
	    * bookmarks of user from table Bookmarks.
	    */
	   public List<BookMarks> listBookMarksofUser(String login);
	   
	   public BookMarks getUserBookmarkOfBook(String login,int book);
	   
	   
}
