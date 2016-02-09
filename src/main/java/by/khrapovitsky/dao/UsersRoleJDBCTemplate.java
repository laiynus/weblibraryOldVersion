package by.khrapovitsky.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.khrapovitsky.mapper.UsersRoleMapper;
import by.khrapovitsky.model.UsersRole;


public class UsersRoleJDBCTemplate implements UsersRoleDAO {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(String login, String role) {
		 String SQL = "insert into User_Roles () values (?, ?)";
		 jdbcTemplateObject.update(SQL, login, role);
		 return;
	}

	public UsersRole getUserRole(String login) {
		String SQL = "select * from User_Roles where Login = ?";
		UsersRole position = jdbcTemplateObject.queryForObject(SQL, new Object[]{login}, new UsersRoleMapper());
		return position;
	}

	public List<UsersRole> listRoles() {
		String SQL = "select * from User_Roles";
		List<UsersRole> position = jdbcTemplateObject.query(SQL, new UsersRoleMapper());
		return position;
	}

	public void delete(Integer id) {
		String SQL = "delete from User_Roles where ID_User_Role = ?";
		jdbcTemplateObject.update(SQL, id);
		return;
	}

	public void update(Integer id, String login, String role) {
		 String SQL = "update User_Roles set Login = ?, Role = ? where ID_User_Role = ?";
		 jdbcTemplateObject.update(SQL, login, role, id);
		  return;
	}

}
