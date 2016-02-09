package by.khrapovitsky.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.khrapovitsky.model.Authors;

public class AuthorsMapper implements RowMapper<Authors>{
	public Authors mapRow(ResultSet rs,int rowNum) throws SQLException{
		Authors author = new Authors();
		author.setId(rs.getInt("ID_Author"));
		author.setLastName(rs.getString("Last_name"));
		author.setFirstName(rs.getString("First_name"));
		return author;
	}
}
