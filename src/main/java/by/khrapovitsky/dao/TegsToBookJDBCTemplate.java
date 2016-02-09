package by.khrapovitsky.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.khrapovitsky.mapper.TegsToBookMapper;
import by.khrapovitsky.model.TegsToBook;



public class TegsToBookJDBCTemplate implements TegsToBookDAO {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(Integer book, Integer teg) {
		String SQL = "insert into Tegs_to_Books values (?,?)";
		 
		jdbcTemplateObject.update(SQL, book, teg);
		return;
		
	}

	public TegsToBook getTegToBook(Integer id) {
		String SQL = "select * from Tegs_to_Books where id = ID_Tegs_to_Books";
		TegsToBook teg2book = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new TegsToBookMapper());
		return teg2book;
	}

	public List<TegsToBook> listTegsToBook() {
		String SQL = "select * from Tegs_to_Books";
		List<TegsToBook> teg2book = jdbcTemplateObject.query(SQL, new TegsToBookMapper());
		return teg2book;
	}

	public void delete(Integer id) {
		String SQL = "delete from Tegs_to_Books where ID_Tegs_to_Books = ?";
		jdbcTemplateObject.update(SQL, id);
		return;
		
	}

	public void update(Integer id, Integer book, Integer teg) {
		 String SQL = "update Tegs_to_Books set FK_Book = ?, FK_Teg = ? where ID_Tegs_to_Books = ?";
		 jdbcTemplateObject.update(SQL, book,teg, id);
		 
		 return;
		
	}

}
