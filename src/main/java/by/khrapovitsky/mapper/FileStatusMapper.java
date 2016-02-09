package by.khrapovitsky.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.khrapovitsky.model.FileStatus;

public class FileStatusMapper implements RowMapper<FileStatus> {
	
	public FileStatus mapRow(ResultSet rs,int rowNum) throws SQLException{
		FileStatus fileStatus = new FileStatus();
		fileStatus.setId(rs.getInt("ID_Status"));
		fileStatus.setBook(rs.getInt("FK_Book"));
		fileStatus.setPerson(rs.getString("Login"));
		fileStatus.setDateUploaded(rs.getDate("Date_Uploaded"));
		return fileStatus;
	}
}
