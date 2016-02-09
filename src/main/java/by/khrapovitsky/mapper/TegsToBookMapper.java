package by.khrapovitsky.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.khrapovitsky.model.TegsToBook;



public class TegsToBookMapper implements RowMapper<TegsToBook> {
	
	public TegsToBook mapRow(ResultSet rs,int rowNum) throws SQLException{
		TegsToBook tegs2book = new TegsToBook();
		tegs2book.setId(rs.getInt("ID_Tegs_to_Books"));
		tegs2book.setBook(rs.getInt("FK_Book"));
		tegs2book.setTeg(rs.getInt("FK_Teg"));
		return tegs2book;
	}
}
