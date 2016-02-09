package by.khrapovitsky.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;



import org.springframework.jdbc.core.RowMapper;

import by.khrapovitsky.model.Books;

public class BooksMapper implements RowMapper<Books> {
	
	public Books mapRow(ResultSet rs,int rowNum) throws SQLException{
		Books book = new Books();
		book.setId(rs.getInt("ID_Book"));
		book.setPublisher(rs.getInt("FK_Publishing_House"));
		book.setFileType(rs.getString("FK_File_Type"));
		book.setDatePublication(rs.getDate("Date_of_Publication"));
		book.setTitle(rs.getString("Title"));
		book.setAnnotation(rs.getString("Annotation"));
		book.setFilePath(rs.getString("FilePath"));
		return book;
	}
	
}
