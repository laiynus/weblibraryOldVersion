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

import by.khrapovitsky.mapper.PublishHousesMapper;
import by.khrapovitsky.model.PublishHouses;


public class PublishHousesJDBCTemplate implements PublishHousesDAO {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	   
	   
	public void setDataSource(DataSource dataSource) {
	    this.dataSource = dataSource;
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public Integer create(final String name, final String town) {
		 final String SQL = "insert into Publishing_Houses(Name,Town) values (?, ?)";
		 KeyHolder keyHolder = new GeneratedKeyHolder();
		 jdbcTemplateObject.update(new PreparedStatementCreator() {
				public PreparedStatement createPreparedStatement(
						Connection connection) throws SQLException {
					PreparedStatement ps = connection.prepareStatement(SQL,
							new String[] { "ID_Publishing_House" });
					ps.setString(1, name);
					ps.setString(2, town);
					return ps;
				}
			}, keyHolder);

			return keyHolder.getKey().intValue();		 
	}

	public PublishHouses getPublishHouse(Integer id) {
		String SQL = "select * from Publishing_Houses where ID_Publishing_House = ?";
		PublishHouses publisher = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new PublishHousesMapper());
		return publisher;
	}

	public List<PublishHouses> listPublishHouses() {
		String SQL = "select * from Publishing_Houses";
		List<PublishHouses> publisher = jdbcTemplateObject.query(SQL, new PublishHousesMapper());
		return publisher;
	}

	public void delete(Integer id) {
		String SQL = "delete from Publishing_Houses where ID_Publishing_House = ?";
		jdbcTemplateObject.update(SQL, id);
		return;
		
	}

	public void update(Integer id, String name, String town) {
		 String SQL = "update Publishing_Houses set Name = ?, Town = ? where ID_Publishing_House = ?";
		 jdbcTemplateObject.update(SQL, name, town, id);
		 
		 return;
		
	}
}
