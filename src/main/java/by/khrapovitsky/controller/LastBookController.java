package by.khrapovitsky.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import by.khrapovitsky.dao.BookMarksJDBCTemplate;
import by.khrapovitsky.dao.BooksJDBCTemplate;
import by.khrapovitsky.ebookparser.Ebook;
import by.khrapovitsky.ebookparser.InstantParser;
import by.khrapovitsky.ebookparser.Parser;
import by.khrapovitsky.model.BookMarks;


@Controller
@RequestMapping("/lastBook")
public class LastBookController {
	
	@RequestMapping(value = "/getLastBook", method = RequestMethod.GET)
	 public @ResponseBody Ebook getLastBook() {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"web-library-dao.xml");
		BookMarksJDBCTemplate bookMarksJDBCTemplate = (BookMarksJDBCTemplate) context
				.getBean("bookmarksJDBCTemplate");
		BooksJDBCTemplate booksJDBCTemplate = (BooksJDBCTemplate) context
				.getBean("booksJDBCTemplate");
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		List<BookMarks> bookMarks = bookMarksJDBCTemplate.listBookMarksofUser(userDetails.getUsername().toString());
		Parser parser = new InstantParser();
		Ebook ebook = null;
		BookMarks lastBookMark = null;
		if(bookMarks.isEmpty()){
			return ebook;
		}else{
			int i = 0;
			for (BookMarks elem : bookMarks){
				if(i==0){
					lastBookMark = elem;
					i++;
				}else{
					if(elem.getLastDateReading().after(lastBookMark.getLastDateReading())){
						lastBookMark = elem;
					}
				}
			}
						
			ebook = parser.parse(booksJDBCTemplate.getBook(lastBookMark.getBook()).getFilePath(),0,false);
			ebook.idBook = booksJDBCTemplate.getBook(lastBookMark.getBook()).getId();
			ebook.currentChapter.number = lastBookMark.getPage();
			ebook.setLastDateReading(lastBookMark.getLastDateReading());
		}
		if (ebook.isOk) {
			return ebook;
		}else{
			return null;
		}
	 }
}
