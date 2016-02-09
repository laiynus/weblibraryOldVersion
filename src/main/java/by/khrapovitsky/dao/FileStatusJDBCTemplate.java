package by.khrapovitsky.dao;

import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import by.khrapovitsky.mapper.FileStatusMapper;
import by.khrapovitsky.model.FileStatus;

public class FileStatusJDBCTemplate implements FileStatusDAO {

	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}

	public void create(Integer book, String person, Date dateUploaded) {
		String SQL = "insert into File_Status (FK_Book,Login,Date_Uploaded) values (?,?,?)";
		 
		jdbcTemplateObject.update(SQL, book, person, dateUploaded);
		return;
		
	}

	public FileStatus getFileStatus(Integer id) {
		String SQL = "select * from File_Status where id = ID_Status";
		FileStatus status = jdbcTemplateObject.queryForObject(SQL, new Object[]{id}, new FileStatusMapper());
		return status;
	}

	public List<FileStatus> listFileStatus() {
		String SQL = "select * from File_Status";
		List<FileStatus> status = jdbcTemplateObject.query(SQL, new FileStatusMapper());
		return status;
	}

	public void delete(Integer id) {
		String SQL = "delete from File_Status where ID_Status = ?";
		jdbcTemplateObject.update(SQL, id);
		return;
		
	}

	public void update(Integer id, Integer book, String person,Date dateUploaded) {
		 String SQL = "update File_Status set FK_Book = ?, Login = ?, Date_Uploaded = ? where ID_Status = ?";
		 jdbcTemplateObject.update(SQL, book,person,dateUploaded, id);
		 
		 return;
		
	}



}
