package by.khrapovitsky.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import by.khrapovitsky.model.FileTypes;

public class FileTypesMapper implements RowMapper<FileTypes> {
	
	public FileTypes mapRow(ResultSet rs,int rowNum) throws SQLException{
		FileTypes fileType = new FileTypes();
		fileType.setFileType(rs.getString("File_Type"));
		fileType.setExtension(rs.getString("Extension"));
		return fileType;
	}

}
