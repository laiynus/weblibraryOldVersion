package by.khrapovitsky.dao;

import java.util.List;

import javax.sql.DataSource;

import by.khrapovitsky.model.Tegs;

interface TegsDAO {
	
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the Tegs table.
	    */
	   public void create(String name);
	   /** 
	    * This is the method to be used to list down
	    * a record from the Tegs table corresponding
	    * to a passed teg id.
	    */
	   public Tegs getTeg(Integer id);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the Tegs table.
	    */
	   public List<Tegs> listTegs();
	   /** 
	    * This is the method to be used to delete
	    * a record from the Tegs table corresponding
	    * to a passed teg id.
	    */
	   public void delete(Integer id);
	   /** 
	    * This is the method to be used to update
	    * a record into the Tegs table.
	    */
	   public void update(Integer id,String name);
}
