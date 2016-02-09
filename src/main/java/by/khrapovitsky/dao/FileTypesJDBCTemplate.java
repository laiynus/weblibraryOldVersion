package by.khrapovitsky.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import by.khrapovitsky.mapper.FileTypesMapper;
import by.khrapovitsky.model.FileTypes;

public class FileTypesJDBCTemplate implements FileTypesDAO {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(String fileType, String extension) {
		String SQL = "insert into File_Types values (?,?)";

		jdbcTemplateObject.update(SQL, fileType, extension);
		return;

	}

	public FileTypes getFileType(String extension) {
		String SQL = "select * from File_Types where Extension = ?";
		FileTypes fileType;
		try {
			fileType = jdbcTemplateObject.queryForObject(SQL,
					new Object[] { extension }, new FileTypesMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return fileType;
	}

	public List<FileTypes> listFileTypes() {
		String SQL = "select * from File_Types";
		List<FileTypes> fileType = jdbcTemplateObject.query(SQL,
				new FileTypesMapper());
		return fileType;
	}

	public void delete(String extension) {
		String SQL = "delete from File_Types where extension = ?";
		jdbcTemplateObject.update(SQL, extension);
		return;
	}

	public void update(String fileType, String extension) {
		String SQL = "update File_Types set File_Type = ?  where extension = ?";
		jdbcTemplateObject.update(SQL, fileType, extension);

		return;
	}

}
