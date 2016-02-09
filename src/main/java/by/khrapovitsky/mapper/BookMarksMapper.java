package by.khrapovitsky.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.khrapovitsky.model.BookMarks;

public class BookMarksMapper implements RowMapper<BookMarks>{
	
	public BookMarks mapRow(ResultSet rs,int rowNum) throws SQLException{
		BookMarks bookmark = new BookMarks();
		bookmark.setId(rs.getInt("ID_Bookmark"));
		bookmark.setBook(rs.getInt("FK_Book"));
		bookmark.setLogin(rs.getString("Login"));
		bookmark.setLastDateReading(rs.getDate("Last_Date_of_Reading"));
		bookmark.setPage(rs.getInt("Chapter"));
		return bookmark;
	}
	
}
