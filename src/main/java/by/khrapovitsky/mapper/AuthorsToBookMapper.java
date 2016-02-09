package by.khrapovitsky.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.khrapovitsky.model.AuthorsToBook;

public class AuthorsToBookMapper implements RowMapper<AuthorsToBook> {
	public AuthorsToBook mapRow(ResultSet rs,int rowNum) throws SQLException{
		AuthorsToBook author2book = new AuthorsToBook();
		author2book.setId(rs.getInt("ID_Authors_to_Books"));
		author2book.setBook(rs.getInt("FK_Book"));
		author2book.setAuthor(rs.getInt("FK_Author"));
		return author2book;
	}
}
