package by.khrapovitsky.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.khrapovitsky.mapper.TegsMapper;
import by.khrapovitsky.model.Tegs;

public class TegsJDBCTemplate implements TegsDAO {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	public void create(String name) {
		String SQL = "insert into Tegs values (?)";
		 
		jdbcTemplateObject.update(SQL, name);
		return;
	}

	public Tegs getTeg(Integer id) {
		String SQL = "select * from Tegs where id = ID_Teg";
		Tegs teg = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new TegsMapper());
		return teg;
	}

	public List<Tegs> listTegs() {
		String SQL = "select * from Tegs";
		List<Tegs> tegs = jdbcTemplateObject.query(SQL, new TegsMapper());
		return tegs;
	}

	public void delete(Integer id) {
		String SQL = "delete from Tegs where ID_Teg = ?";
		jdbcTemplateObject.update(SQL, id);
		return;
		
	}

	public void update(Integer id, String name) {
		 String SQL = "update Tegs set Name = ? where ID_Teg = ?";
		 jdbcTemplateObject.update(SQL, name, id);
		 
		 return;
		
	}
	
}
