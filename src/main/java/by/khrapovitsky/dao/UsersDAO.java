package by.khrapovitsky.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import by.khrapovitsky.model.Users;

interface UsersDAO {
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the Staff table.
	    */
	   public void create(String login, String lastName, String firstName, String patronymic, String password,String email,Date dateRegistration, Integer enabled, String faculty, String chair, String specialty);
	   /** 
	    * This is the method to be used to list down
	    * a record from the Staff table corresponding
	    * to a passed staff id.
	    */
	   public Users getUser(String login);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the Staff table.
	    */
	   public List<Users> listUsers();
	   /** 
	    * This is the method to be used to delete
	    * a record from the Staff table corresponding
	    * to a passed staff id.
	    */
	   public void delete(String login);
	   /** 
	    * This is the method to be used to update
	    * a record into the Staff table.
	    */
	   public void update(String login, String lastName, String firstName, String patronymic, String faculty, String chair, String specialty);
	   /** 
	    * This is the method to be used to registration user
	    * a record into the User table.
	    */
	   public void registration(String login,String password, String email,Date dateRegistration,Integer enabled);
	   
	   public Users getUserByEmail(String email);
	   
	   public void resetPassword(String login,String password);
	   
	   public void activateUser(String login, Integer enabled);
	
}
