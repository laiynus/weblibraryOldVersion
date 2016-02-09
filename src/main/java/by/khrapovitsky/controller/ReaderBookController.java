package by.khrapovitsky.controller;


import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import by.khrapovitsky.dao.BookMarksJDBCTemplate;
import by.khrapovitsky.dao.BooksJDBCTemplate;
import by.khrapovitsky.ebookparser.Ebook;
import by.khrapovitsky.ebookparser.InstantParser;
import by.khrapovitsky.ebookparser.Parser;
import by.khrapovitsky.model.BookMarks;




@Controller
@RequestMapping("/readerBook")
public class ReaderBookController {
	
	
	@RequestMapping(value = "/getChapter", method = RequestMethod.POST)
	 public @ResponseBody Ebook getChapter(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Ebook requesValue;
		requesValue = mapper.readValue(json, Ebook.class);
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"web-library-dao.xml");
		BooksJDBCTemplate booksJDBCTemplate = (BooksJDBCTemplate) context
				.getBean("booksJDBCTemplate");
		Parser parser = new InstantParser();
		BookMarksJDBCTemplate bookMarksJDBCTemplate = (BookMarksJDBCTemplate) context
				.getBean("bookmarksJDBCTemplate");
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		List<BookMarks> bookMarks = bookMarksJDBCTemplate.listBookMarksofUser(userDetails.getUsername().toString());
		BookMarks lastBookMark = null;
		Ebook ebook = null;
		Boolean flag = false;
		if(bookMarks.isEmpty()){
			 ebook = parser.parse(booksJDBCTemplate.getBook(requesValue.getIdBook()).getFilePath(),1,false);
			 ebook.idBook = requesValue.getIdBook();
			 ebook.currentChapter.number = 1;
		}else{
			
			for (BookMarks elem : bookMarks){
				if(elem.getBook()==requesValue.getIdBook()){
					lastBookMark = elem;
					flag=true;
					break;
				}
			}
			
			if(flag==true){
				 ebook = parser.parse(booksJDBCTemplate.getBook(requesValue.getIdBook()).getFilePath(),lastBookMark.getPage(),false);
				 ebook.idBook = requesValue.getIdBook();
				 ebook.currentChapter.number = lastBookMark.getPage();
				 
			}else{
				 ebook = parser.parse(booksJDBCTemplate.getBook(requesValue.getIdBook()).getFilePath(),1,false);
				 ebook.idBook = requesValue.getIdBook();
				 ebook.currentChapter.number = 1;
			}
		    
		  
		}
		if (ebook.isOk) {
			return ebook;
		}else{
			return null;
		}
		
	}
	
	@RequestMapping(value = "/getNextPrevChapter", method = RequestMethod.POST)
	 public @ResponseBody Ebook getNextPrevChapter(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Ebook requesValue;
		requesValue = mapper.readValue(json, Ebook.class);
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"web-library-dao.xml");
		BooksJDBCTemplate booksJDBCTemplate = (BooksJDBCTemplate) context
				.getBean("booksJDBCTemplate");
		Parser parser = new InstantParser();
		
		
		Ebook ebook = null;
		ebook = parser.parse(booksJDBCTemplate.getBook(requesValue.getIdBook()).getFilePath(),requesValue.getPage(),false);
		ebook.idBook = requesValue.getIdBook();
		ebook.currentChapter.number = requesValue.getPage();
		if (ebook.isOk) {
			return ebook;
		}else{
			return null;
		}
		
	}
	
	@RequestMapping(value = "/saveBookMark", method = RequestMethod.POST)
	 public void saveBookMark(@RequestBody String json) throws JsonParseException, JsonMappingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		Ebook requesValue;
		requesValue = mapper.readValue(json, Ebook.class);
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"web-library-dao.xml");
		BookMarksJDBCTemplate bookMarksJDBCTemplate = (BookMarksJDBCTemplate) context
				.getBean("bookmarksJDBCTemplate");
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		BookMarks bookMarks = bookMarksJDBCTemplate.getUserBookmarkOfBook(userDetails.getUsername(), requesValue.idBook);
		if(bookMarks==null){
			bookMarksJDBCTemplate.create(requesValue.idBook, userDetails.getUsername(), new Date(), requesValue.page);
		}else{
			if(bookMarks.getPage()!=requesValue.page)
			bookMarksJDBCTemplate.update(bookMarks.getId(), requesValue.idBook, userDetails.getUsername(), new Date(), requesValue.page);
		}
		
		
	}
	
}
