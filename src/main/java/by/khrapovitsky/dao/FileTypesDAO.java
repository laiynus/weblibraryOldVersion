package by.khrapovitsky.dao;

import java.util.List;

import javax.sql.DataSource;

import by.khrapovitsky.model.FileTypes;

interface FileTypesDAO {
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the FileTypes table.
	    */
	   public void create(String fileType, String extension);
	   /** 
	    * This is the method to be used to list down
	    * a record from the FileTypes table corresponding
	    * to a passed fileType id.
	    */
	   public FileTypes getFileType(String extension);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the FileTypes table.
	    */
	   public List<FileTypes> listFileTypes();
	   /** 
	    * This is the method to be used to delete
	    * a record from the FileTypes table corresponding
	    * to a passed fileType id.
	    */
	   public void delete(String extension);
	   /** 
	    * This is the method to be used to update
	    * a record into the FileTypes table.
	    */
	   public void update(String fileType, String extension);
}
