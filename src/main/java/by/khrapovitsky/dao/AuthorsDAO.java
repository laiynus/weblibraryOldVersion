package by.khrapovitsky.dao;

import java.util.List;

import javax.sql.DataSource;

import by.khrapovitsky.model.Authors;

interface AuthorsDAO {
	/** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the Authors table.
	    */
	   public Integer create(final String firstName, final String lastName);
	   /** 
	    * This is the method to be used to list down
	    * a record from the Authors table corresponding
	    * to a passed author id.
	    */
	   public Authors getAuthor(Integer id);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the Authors table.
	    */
	   public List<Authors> listAuthors();
	   /** 
	    * This is the method to be used to delete
	    * a record from the Authors table corresponding
	    * to a passed author id.
	    */
	   public void delete(Integer id);
	   /** 
	    * This is the method to be used to update
	    * a record into the Authors table.
	    */
	   public void update(Integer id,String firstName, String lastName);
}
