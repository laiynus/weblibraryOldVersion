package by.khrapovitsky.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.khrapovitsky.model.Users;

public class UsersMapper implements RowMapper<Users> {
	public Users mapRow(ResultSet rs, int rowNum) throws SQLException {
		  Users user = new Users();
		  user.setLogin(rs.getString("Login"));
		  user.setLastName(rs.getString("Last_name"));
		  user.setFirstName(rs.getString("First_name"));
		  user.setPatronymic(rs.getString("Patronymic"));
		  user.setPassword(rs.getString("Password"));
		  user.setEnabled(rs.getInt("Enabled"));
		  user.setEmail(rs.getString("Email"));
		  user.setDateRegistration(rs.getDate("Date_of_Registration"));
		  user.setFaculty(rs.getString("Faculty"));
		  user.setChair(rs.getString("Chair"));
		  user.setSpecialty(rs.getString("Specialty"));
		  return user;
	}
}
