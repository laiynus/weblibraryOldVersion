package by.khrapovitsky.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.khrapovitsky.model.UsersRole;


public class UsersRoleMapper implements RowMapper<UsersRole>{
	public UsersRole mapRow(ResultSet rs,int rowNum) throws SQLException{
		UsersRole positions = new UsersRole();
		positions.setId(rs.getInt("ID_User_Role"));
		positions.setLogin(rs.getString("Login"));
		positions.setRole(rs.getString("Role"));
		return positions;
	}
}
