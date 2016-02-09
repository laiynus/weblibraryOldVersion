package by.khrapovitsky.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.khrapovitsky.model.Tegs;

public class TegsMapper implements RowMapper<Tegs> {
	
	public Tegs mapRow(ResultSet rs,int rowNum) throws SQLException{
		Tegs teg = new Tegs();
		teg.setId(rs.getInt("ID_Teg"));
		teg.setName(rs.getString("Name"));
		return teg;
	}
}
