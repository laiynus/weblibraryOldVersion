package by.khrapovitsky.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import by.khrapovitsky.crypt.EncoderGenerator;
import by.khrapovitsky.mapper.UsersMapper;
import by.khrapovitsky.model.Users;



public class UsersJDBCTemplate implements UsersDAO {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(String login, String lastName, String firstName,
			String patronymic, String password, String email,
			Date dateRegistration, Integer enabled, String faculty,
			String chair, String specialty) {
		String SQL = "insert into Users () values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
		password = new EncoderGenerator().passwordGenerator(password);
		jdbcTemplateObject.update(SQL, login, lastName, firstName, patronymic,
				password, email, dateRegistration, enabled, faculty, chair,
				specialty);

		return;
	}

	public Users getUser(String login) {
		String SQL = "select * from Users where Login = ?";
		Users staff;
		try {
			staff = jdbcTemplateObject.queryForObject(SQL,
					new Object[] { login }, new UsersMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return staff;
	}

	public List<Users> listUsers() {
		String SQL = "select * from Users";
		List<Users> staff = jdbcTemplateObject.query(SQL, new UsersMapper());
		return staff;
	}

	public void delete(String login) {
		String SQL = "delete from Users where Login = ?";
		jdbcTemplateObject.update(SQL, login);
		return;
	}

	public void update(String login, String lastName, String firstName,
			String patronymic, String faculty, String chair, String specialty) {
		String SQL = "update Users set  Last_name = ?, First_name = ?, Patronymic = ?,  Faculty = ?, Chair = ?, Specialty = ? where Login = ?";
		jdbcTemplateObject.update(SQL, lastName, firstName, patronymic,
				faculty, chair, specialty, login);
		return;
	}
	
	public void activateUser(String login, Integer enabled) {
		String SQL = "update Users set  Enabled = ? where Login = ?";
		jdbcTemplateObject.update(SQL, enabled, login);
		return;
	}
	
	public void registration(String login,String password, String email,Date dateRegistration,Integer enabled) {
		String SQL = "insert into Users(Login, Password, Email, Date_of_Registration, Enabled) values ( ?, ?, ?, ?, ?)";
		password = new EncoderGenerator().passwordGenerator(password);
		jdbcTemplateObject.update(SQL, login, password, email, dateRegistration,
				enabled);

		return;
	}

	public Users getUserByEmail(String email) {
		String SQL = "select * from Users where Email = ?";
		Users staff;
		try {
			staff = jdbcTemplateObject.queryForObject(SQL,
					new Object[] { email }, new UsersMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return staff;
	}
	
	public void resetPassword(String login,String password){
		String SQL = "update Users set  Password = ? where Login = ?";
		password = new EncoderGenerator().passwordGenerator(password);
		jdbcTemplateObject.update(SQL, password,login);
		return;
	}
}
