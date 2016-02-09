package by.khrapovitsky.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import by.khrapovitsky.model.FileStatus;

interface FileStatusDAO {
	
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the FileStatus table.
	    */
	   public void create(Integer book,String person,Date dateUploaded);
	   /** 
	    * This is the method to be used to list down
	    * a record from the FileStatus table corresponding
	    * to a passed status id.
	    */
	   public FileStatus getFileStatus(Integer id);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the FileStatus table.
	    */
	   public List<FileStatus> listFileStatus();
	   /** 
	    * This is the method to be used to delete
	    * a record from the FileStatus table corresponding
	    * to a passed status id.
	    */
	   public void delete(Integer id);
	   /** 
	    * This is the method to be used to update
	    * a record into the FileStatus table.
	    */
	   public void update(Integer id,Integer book,String person,Date dateUploaded);
}
