package by.khrapovitsky.dao;

import java.util.List;

import javax.sql.DataSource;

import by.khrapovitsky.model.UsersRole;


interface UsersRoleDAO {
	   /** 
	    * This is the method to be used to initialize
	    * database resources ie. connection.
	    */
	   public void setDataSource(DataSource ds);
	   /** 
	    * This is the method to be used to create
	    * a record in the Positions table.
	    */
	   public void create(String login, String role);
	   /** 
	    * This is the method to be used to list down
	    * a record from the Positions table corresponding
	    * to a passed post id.
	    */
	   public UsersRole getUserRole(String login);
	   /** 
	    * This is the method to be used to list down
	    * all the records from the Positions table.
	    */
	   public List<UsersRole> listRoles();
	   /** 
	    * This is the method to be used to delete
	    * a record from the Positions table corresponding
	    * to a passed post id.
	    */
	   public void delete(Integer id);
	   /** 
	    * This is the method to be used to update
	    * a record into the Positions table.
	    */
	   public void update(Integer id,String login, String role);
}
