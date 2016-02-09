package by.khrapovitsky.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import by.khrapovitsky.mapper.AuthorsMapper;
import by.khrapovitsky.model.Authors;

public class AuthorsJDBCTemplate implements AuthorsDAO{
	
	 @SuppressWarnings("unused")
	 private DataSource dataSource;
	 private JdbcTemplate jdbcTemplateObject;
	
	 public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	 }
	 
	 public Integer create(final String firstName, final String lastName){
		 final String SQL = "insert into Authors (Last_name, First_name) values ( ?, ?)";
		 			 
		 KeyHolder keyHolder = new GeneratedKeyHolder();
		 jdbcTemplateObject.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(SQL,
							new String[] { "ID_Author" });
					ps.setString(1, firstName);
					ps.setString(2,  lastName);
					
					return ps;
				}
			}, keyHolder);

			return keyHolder.getKey().intValue();		 
	 }
	 
	 public Authors getAuthor(Integer id){
		 String SQL = "select * from Authors where ID_Author = ?";
		 Authors author = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new AuthorsMapper());
		 return author;
	 }
	 
	 public List<Authors> listAuthors(){
		 String SQL = "select * from Authors";
		 List<Authors> author = jdbcTemplateObject.query(SQL, new AuthorsMapper());
		 return author;
	 }
	 
	 public void delete(Integer id){
		 String SQL = "delete from Authors where ID_Author = ?";
		 jdbcTemplateObject.update(SQL, id);
		 return;
	 }
	 
	 public void update(Integer id,String firstName, String lastName){
		
		 String SQL = "update Authors set Last_name = ?, First_name = ?,  where ID_Author = ?";
		 jdbcTemplateObject.update(SQL, lastName, firstName, id);
		 
		 return;
	 }

}
