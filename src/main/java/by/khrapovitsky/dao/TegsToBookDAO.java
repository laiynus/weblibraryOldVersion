package by.khrapovitsky.dao;

import java.util.List;

import javax.sql.DataSource;

import by.khrapovitsky.model.TegsToBook;



interface TegsToBookDAO {
	
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the TegsToBook table.
	    */
	   public void create(Integer book,Integer teg);
	   /** 
	    * This is the method to be used to list down
	    * a record from the TegsToBook table corresponding
	    * to a passed teg id.
	    */
	   public TegsToBook getTegToBook(Integer id);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the TegsToBook table.
	    */
	   public List<TegsToBook> listTegsToBook();
	   /** 
	    * This is the method to be used to delete
	    * a record from the TegsToBook table corresponding
	    * to a passed status teg.
	    */
	   public void delete(Integer id);
	   /** 
	    * This is the method to be used to update
	    * a record into the TegsToBook table.
	    */
	   public void update(Integer id,Integer book,Integer teg);
	   
}
