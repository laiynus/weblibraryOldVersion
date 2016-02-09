package by.khrapovitsky.dao;


import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import by.khrapovitsky.model.Books;

interface BooksDAO {
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the Book table.
	    */
	   public Integer create(Integer publisher, String fileType,Date datePublication, String title, String annotation, String filePath);
	   /** 
	    * This is the method to be used to list down
	    * a record from the Books table corresponding
	    * to a passed book id.
	    */
	   public Books getBook(Integer id);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the Book table.
	    */
	   public List<Books> listBooks();
	   /** 
	    * This is the method to be used to delete
	    * a record from the Books table corresponding
	    * to a passed book id.
	    */
	   public void delete(Integer id);
	   /** 
	    * This is the method to be used to update
	    * a record into the Book table.
	    */
	   public void update(Integer id, Integer publisher, String fileType,Date datePublication ,String title,  String annotation, String filePath);
}
