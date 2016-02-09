package by.khrapovitsky.dao;

import java.util.List;

import javax.sql.DataSource;



import by.khrapovitsky.model.PublishHouses;

interface PublishHousesDAO {
	
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the PublishHouse table.
	    */
	   public Integer create(String name, String town);
	   /** 
	    * This is the method to be used to list down
	    * a record from the PublishHouse table corresponding
	    * to a passed publisher id.
	    */
	   public PublishHouses getPublishHouse(Integer id);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the PublishHouse table.
	    */
	   public List<PublishHouses> listPublishHouses();
	   /** 
	    * This is the method to be used to delete
	    * a record from the PublishHouse table corresponding
	    * to a passed publisher id.
	    */
	   public void delete(Integer id);
	   /** 
	    * This is the method to be used to update
	    * a record into the PublishHouse table.
	    */
	   public void update(Integer id,String name, String towns);
}
