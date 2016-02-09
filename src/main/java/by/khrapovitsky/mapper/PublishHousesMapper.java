package by.khrapovitsky.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.khrapovitsky.model.PublishHouses;

public class PublishHousesMapper implements RowMapper<PublishHouses> {
	
	public PublishHouses mapRow(ResultSet rs, int rowNum) throws SQLException {
		  PublishHouses publisher = new PublishHouses();
		  publisher.setId(rs.getInt("ID_Publishing_House"));
		  publisher.setName(rs.getString("Name"));
		  publisher.setTown(rs.getString("Town"));
		  return publisher;
	}
	
}
