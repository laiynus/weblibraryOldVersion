package by.khrapovitsky.dao;

import java.util.List;

import javax.sql.DataSource;

import by.khrapovitsky.model.AuthorsToBook;

interface AuthorsToBookDAO {
	   
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the AuthorToBook table.
	    */
	   public void create(Integer book, Integer author);
	   /** 
	    * This is the method to be used to list down
	    * a record from the AuthorToBook table corresponding
	    * to a passed author2book id.
	    */
	   public AuthorsToBook getAuthorToBook(Integer fk_book, Integer fk_author);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the AuthorToBook table.
	    */
	   public List<AuthorsToBook> listAuthorToBook();
	   /** 
	    * This is the method to be used to delete
	    * a record from the AuthorToBook table corresponding
	    * to a passed author2book id.
	    */
	   public void delete(Integer id);
	   /** 
	    * This is the method to be used to update
	    * a record into the AuthorToBook table.
	    */
	   public void update(Integer id,String book, String author);
	   
	   public AuthorsToBook getAuthor(Integer fk_book );
}
