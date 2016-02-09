package by.khrapovitsky.controller;


import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.khrapovitsky.dao.AuthorsJDBCTemplate;
import by.khrapovitsky.dao.AuthorsToBookJDBCTemplate;
import by.khrapovitsky.dao.BookMarksJDBCTemplate;
import by.khrapovitsky.dao.BooksJDBCTemplate;
import by.khrapovitsky.dao.PublishHousesJDBCTemplate;
import by.khrapovitsky.datatable.DataTablesParamUtility;
import by.khrapovitsky.datatable.JQueryDataTableParamModel;
import by.khrapovitsky.model.BookMarks;
import by.khrapovitsky.model.Books;

@Controller
@RequestMapping("/mybookmarks")
public class UserBookMarks {
	
	@RequestMapping
	public ModelAndView users() {

		ModelAndView model = new ModelAndView();
		model.setViewName("/main/mybookmarks");
		return model;

	}
	
	@RequestMapping(value = "/springPaginationDataTables.web", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody void springPaginationDataTables(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		final JQueryDataTableParamModel param = DataTablesParamUtility
				.getParam(request);

		String sEcho = param.sEcho;
		int iTotalRecords; // total number of records (unfiltered)
		int iTotalDisplayRecords;// value will be set when code filters
									// companies by keyword
		JSONArray data = new JSONArray(); // data that will be shown in the
											// table

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"web-library-dao.xml");
		BookMarksJDBCTemplate bookmarksJDBCTemplate = (BookMarksJDBCTemplate) context
				.getBean("bookmarksJDBCTemplate");
		BooksJDBCTemplate booksJDBCTemplate = (BooksJDBCTemplate) context
				.getBean("booksJDBCTemplate");
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		PublishHousesJDBCTemplate publishHouseJDBCTemplate = (PublishHousesJDBCTemplate) context
				.getBean("publishHouseJDBCTemplate");
		AuthorsJDBCTemplate authorsJDBCTemplate = (AuthorsJDBCTemplate) context
				.getBean("authorsJDBCTemplate");
		AuthorsToBookJDBCTemplate authorsToBookJDBCTemplate = (AuthorsToBookJDBCTemplate) context
				.getBean("authorsToBookJDBCTemplate");
		List<BookMarks> bookmarks = bookmarksJDBCTemplate.listBookMarksofUser(userDetails.getUsername());

		iTotalRecords = bookmarks.size();
		for (BookMarks elem : bookmarks) {
			Books book = booksJDBCTemplate.getBook(elem.getBook());
			book.setAuthor(authorsJDBCTemplate.getAuthor(authorsToBookJDBCTemplate.getAuthor(book.getId()).getAuthor()));
			book.setPublisherHouse(publishHouseJDBCTemplate.getPublishHouse(book.getPublisher()));
			book.setFullname(book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName());
			elem.setBooks(book);
		}
		List<BookMarks> bookmark = new LinkedList<BookMarks>();
	
		for (BookMarks c : bookmarks) {
			// Cannot search by column 0 (id)
			if (param.bSearchable[2]
					&& c.getBooks().getTitle().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())
					|| param.bSearchable[3]
					&& c.getBooks().getFullname().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())
					|| param.bSearchable[4]
					&& c.getBooks().getPublisherHouse().getName().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())
					|| param.bSearchable[4]
					&& c.getBooks().getDatePublication().toString().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())
					|| param.bSearchable[6]
					&& c.getLastDateReading().toString().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())
					|| param.bSearchable[7]
					&& Integer.toString(c.getPage()).toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase()))
			{
				bookmark.add(c); // Add a company that matches search criterion
			}
		}
		iTotalDisplayRecords = bookmark.size();// Number of companies that matches
											// search criterion should be
											// returned

		Collections.sort(bookmark, new Comparator<BookMarks>() {
			@Override
			public int compare(BookMarks c1, BookMarks c2) {
				int result = 0;
				for (int i = 0; i < param.iSortingCols; i++) {
					int sortBy = param.iSortCol[i];
					if (param.bSortable[sortBy]) {
						switch (sortBy) {
						case 2:
							result = c1.getBooks().getTitle().compareToIgnoreCase(
									c2.getBooks().getTitle())
									* (param.sSortDir[i].equals("asc") ? -1 : 1);
							break;
						case 3:
							result = c1.getBooks().getFullname().compareToIgnoreCase(
									c2.getBooks().getFullname())
									* (param.sSortDir[i].equals("asc") ? -1 : 1);
							break;
						case 4:
							result = c1.getBooks().getPublisherHouse().getName().compareToIgnoreCase(
									c2.getBooks().getPublisherHouse().getName())
									* (param.sSortDir[i].equals("asc") ? -1 : 1);
							break;	
						case 5:
							result = c1.getBooks().getDatePublication().toString().compareToIgnoreCase(
									c2.getBooks().getDatePublication().toString())
									* (param.sSortDir[i].equals("asc") ? -1 : 1);
							break;		
						case 6:
							result = c1
									.getLastDateReading()
									.toString()
									.compareToIgnoreCase(
											c2.getLastDateReading().toString())
									* (param.sSortDir[i].equals("asc") ? -1 : 1);
							break;
						case 7:
							result = Integer.toString(c1.getPage()).compareToIgnoreCase(
									Integer.toString(c2.getPage()))
									* (param.sSortDir[i].equals("asc") ? -1 : 1);
							break;
					
						}
					}
					if (result != 0)
						return result;
					else
						continue;
				}
				return result;
			}
		});

		if (bookmark.size() < param.iDisplayStart + param.iDisplayLength)
			bookmark = bookmark.subList(param.iDisplayStart, bookmark.size());
		else
			bookmark = bookmark.subList(param.iDisplayStart, param.iDisplayStart
					+ param.iDisplayLength);

		try {
			JSONObject jsonResponse = new JSONObject();

			jsonResponse.put("sEcho", sEcho);
			jsonResponse.put("iTotalRecords", iTotalRecords);
			jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);

			for (BookMarks c : bookmark) {
				JSONArray row = new JSONArray();
				row.put(c.getBooks().getId()).put(c.getId()).put(c.getBooks().getTitle()).put(c.getBooks().getFullname())
						.put(c.getBooks().getPublisherHouse().getName()).put(c.getBooks().getDatePublication()).put(c.getLastDateReading()).put(c.getPage());
				data.put(row);
			}
			jsonResponse.put("aaData", data);

			response.setContentType("application/json");
			response.getWriter().print(jsonResponse.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			response.setContentType("text/html");
			response.getWriter().print(e.getMessage());
		}

	}
	
	@RequestMapping(value = "/deleteBookMark", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody void deleteBookMark(
			HttpServletRequest request, HttpServletResponse response) throws IOException{
			int id_bookmark = Integer.parseInt(request.getParameter("id"));
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"web-library-dao.xml");
			BookMarksJDBCTemplate bookmarksJDBCTemplate = (BookMarksJDBCTemplate) context
					.getBean("bookmarksJDBCTemplate");
			try{
				
				bookmarksJDBCTemplate.delete(id_bookmark);
			}catch (Exception e){
				e.printStackTrace();
				response.getWriter().print("Bookmark don't exist or it's already removed!");
				return;
			}
			bookmarksJDBCTemplate.delete(id_bookmark);
			response.getWriter().print("Bookmark is successfully removed!");
			return;
	}
}
