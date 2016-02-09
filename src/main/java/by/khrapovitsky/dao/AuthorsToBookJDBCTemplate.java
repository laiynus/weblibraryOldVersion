package by.khrapovitsky.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.khrapovitsky.mapper.AuthorsToBookMapper;
import by.khrapovitsky.model.AuthorsToBook;

public class AuthorsToBookJDBCTemplate implements AuthorsToBookDAO {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(Integer book, Integer author) {
		 String SQL = "insert into Authors_to_Books (FK_Book, FK_Author) values (?, ?)";
		 
		 jdbcTemplateObject.update(SQL, book, author);
		 return;
		
	}

	public AuthorsToBook getAuthorToBook(Integer fk_book, Integer fk_author) {
		String SQL = "select * from Authors_to_Books where where FK_Book = ? and FK_Author = ?";
		AuthorsToBook author2book = jdbcTemplateObject.queryForObject(SQL, new Object[]{fk_book,fk_author}, new AuthorsToBookMapper());
		return author2book;
	}
	

	public AuthorsToBook getAuthor(Integer fk_book ) {
		String SQL = "select * from Authors_to_Books where FK_Book = ? ";
		AuthorsToBook author2book = jdbcTemplateObject.queryForObject(SQL, new Object[]{fk_book}, new AuthorsToBookMapper());
		return author2book;
	}

	public List<AuthorsToBook> listAuthorToBook() {
		String SQL = "select * from Authors_to_Books";
		List<AuthorsToBook> author2book = jdbcTemplateObject.query(SQL, new AuthorsToBookMapper());
		return author2book;
	}

	public void delete(Integer id) {
		String SQL = "delete from Authors_to_Books where ID_Authors_to_Books = ?";
		jdbcTemplateObject.update(SQL, id);
		return;
		
	}

	public void update(Integer id, String book, String author) {
		 String SQL = "update Authors_to_Books set FK_Book = ?, FK_Author = ? where ID_Authors_to_Books = ?";
		 jdbcTemplateObject.update(SQL, book, author, id);
		 
		 return;
		
	}

}
