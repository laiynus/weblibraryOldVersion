package by.khrapovitsky.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import by.khrapovitsky.mapper.BooksMapper;
import by.khrapovitsky.model.Books;

public class BooksJDBCTemplate implements BooksDAO {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public Integer create(final Integer publisher, final String fileType,
			final Date datePublication, final String title, 
			final String annotation, final String filePath) {
		final String SQL = "insert into Books(FK_Publishing_House, FK_File_Type, Date_of_Publication, Title,  Annotation,FilePath) values ( ?, ?, ?, ?, ?, ?)";
		KeyHolder keyHolder = new GeneratedKeyHolder();
		 jdbcTemplateObject.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(SQL,
							new String[] { "ID_Book" });
					ps.setInt(1, publisher);
					ps.setString(2, fileType);
					ps.setDate(3,  datePublication);
					ps.setString(4, title);
					ps.setString(5, annotation);
					ps.setString(6, filePath);
					return ps;
				}
			}, keyHolder);

			return keyHolder.getKey().intValue();		 
	}

	public Books getBook(Integer id) {
		String SQL = "select * from Books where ID_Book = ?";
		Books book = jdbcTemplateObject.queryForObject(SQL,
				new Object[] { id }, new BooksMapper());
		return book;
	}

	public List<Books> listBooks() {
		String SQL = "select * from Books";
		List<Books> books = jdbcTemplateObject.query(SQL, new BooksMapper());
		return books;
	}

	public void delete(Integer id) {
		String SQL = "delete from Books where ID_Book = ?";
		jdbcTemplateObject.update(SQL, id);
		return;
	}

	public void update(Integer id, Integer publisher, String fileType,
			Date datePublication, String title, 
			String annotation, String filePath) {

		String SQL = "update Books set FK_Publishing_House = ?, FK_File_Type = ?, Date_of_Publication = ?, Title = ?,  Annotation = ?, FilePath = ? where ID_Book = ?";
		jdbcTemplateObject.update(SQL, publisher, fileType, datePublication,
				title,  annotation, filePath, id);

		return;
	}


}
