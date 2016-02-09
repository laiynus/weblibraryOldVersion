package by.khrapovitsky.dao;


import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import by.khrapovitsky.mapper.BookMarksMapper;
import by.khrapovitsky.model.BookMarks;


public class BookMarksJDBCTemplate implements BookMarksDAO{

	 @SuppressWarnings("unused")
	 private DataSource dataSource;
	 private JdbcTemplate jdbcTemplateObject;
	
	 public void setDataSource(DataSource dataSource) {
	      this.dataSource = dataSource;
	      this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	 }
	 
	 public void create(Integer book, String login,Date lastDateReading,Integer page){
		 String SQL = "insert into Bookmarks(FK_Book, Login, Last_Date_of_Reading, Chapter) values (?, ?, ?,?)";
		 		
		 jdbcTemplateObject.update(SQL, book, login, lastDateReading,page);
		 return;
	 }
	 
	 public BookMarks getBookMark(Integer id){
		 String SQL = "select * from Bookmarks where ID_Bookmark = ?";
		 BookMarks bookmark = jdbcTemplateObject.queryForObject(SQL,new Object[]{id},new BookMarksMapper());
		 return bookmark;
	 }
	 
	 public List<BookMarks> listBookMarks(){
		 String SQL = "select * from Bookmarks";
		 List<BookMarks> bookmark = jdbcTemplateObject.query(SQL, new BookMarksMapper());
		 return bookmark;
	 }
	 
	 public void delete(Integer id){
		 String SQL = "delete from Bookmarks where ID_Bookmark = ?";
		 jdbcTemplateObject.update(SQL, id);
		 return;
	 }
	 
	 public void update(Integer id, Integer book, String login,Date lastDateReading,Integer page){
		
		 String SQL = "update Bookmarks set FK_Book = ?, Login = ?, Last_Date_of_Reading = ?, Chapter = ? where ID_Bookmark = ?";
		 jdbcTemplateObject.update(SQL, book, login, lastDateReading,page, id);
		 
		 return;
	 }


	public List<BookMarks> listBookMarksofUser(String login) {
		String SQL = "select * from Bookmarks where Login = ?";
		List<BookMarks> bookmark;
		try {
			bookmark = jdbcTemplateObject.query(SQL,new Object[]{login}, new BookMarksMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	
		return bookmark;
	}
	
	public BookMarks getUserBookmarkOfBook(String login,int book) {
		String SQL = "select * from Bookmarks where Login = ? and FK_Book = ?";
		BookMarks bookmark = null;
		try {
			bookmark = jdbcTemplateObject.queryForObject(SQL,
					new Object[] { login, book }, new BookMarksMapper());
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return bookmark;
	}
	 
}
