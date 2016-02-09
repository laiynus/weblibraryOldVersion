package by.khrapovitsky.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import by.khrapovitsky.dao.AuthorsJDBCTemplate;
import by.khrapovitsky.dao.AuthorsToBookJDBCTemplate;
import by.khrapovitsky.dao.BooksJDBCTemplate;
import by.khrapovitsky.dao.PublishHousesJDBCTemplate;
import by.khrapovitsky.datatable.DataTablesParamUtility;
import by.khrapovitsky.datatable.JQueryDataTableParamModel;
import by.khrapovitsky.model.Authors;
import by.khrapovitsky.model.Books;


@Controller
@RequestMapping("/books")
public class BooksContoller {
	
	@RequestMapping
	public ModelAndView booksTable() {

		ModelAndView model = new ModelAndView();
		model.setViewName("/main/books");
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
		BooksJDBCTemplate booksJDBCTemplate = (BooksJDBCTemplate) context
				.getBean("booksJDBCTemplate");
		PublishHousesJDBCTemplate publishHouseJDBCTemplate = (PublishHousesJDBCTemplate) context
				.getBean("publishHouseJDBCTemplate");
		AuthorsJDBCTemplate authorsJDBCTemplate = (AuthorsJDBCTemplate) context
				.getBean("authorsJDBCTemplate");
		AuthorsToBookJDBCTemplate authorsToBookJDBCTemplate = (AuthorsToBookJDBCTemplate) context
				.getBean("authorsToBookJDBCTemplate");
		List<Books> books = booksJDBCTemplate.listBooks();
		for (Books elem : books) {
			elem.setPublisherHouse(publishHouseJDBCTemplate.getPublishHouse(elem.getPublisher()));
			Authors author = authorsJDBCTemplate.getAuthor(authorsToBookJDBCTemplate.getAuthor(elem.getId()).getAuthor());
			elem.setFullname(author.getFirstName() + " " + author.getLastName());
		}
		iTotalRecords = books.size();
		List<Books> book = new LinkedList<Books>();
		for (Books c : books) {
			// Cannot search by column 0 (id)
			if (param.bSearchable[1]
					&& c.getTitle().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())
					|| param.bSearchable[2]
					&& c.getFullname().toString().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())
					|| param.bSearchable[3]
					&& c.getPublisherHouse().getName().toString().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())
					|| param.bSearchable[4]
					&& c.getDatePublication().toString().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())
					|| param.bSearchable[5]
					&& c.getFileType().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())) {
				book.add(c); // Add a company that matches search criterion
			}
		}
		iTotalDisplayRecords = book.size();// Number of companies that matches
											// search criterion should be
											// returned

		Collections.sort(book, new Comparator<Books>() {
			@Override
			public int compare(Books c1, Books c2) {
				int result = 0;
				for (int i = 0; i < param.iSortingCols; i++) {
					int sortBy = param.iSortCol[i];
					if (param.bSortable[sortBy]) {
						switch (sortBy) {
						case 1:
							result = c1.getTitle().compareToIgnoreCase(
									c2.getTitle())
									* (param.sSortDir[i].equals("asc") ? -1 : 1);
							break;
						case 2:
							result = c1
									.getFullname()
									.toString()
									.compareToIgnoreCase(
											c2.getAuthor().getLastName().toString())
									* (param.sSortDir[i].equals("asc") ? -1 : 1);
							break;
						case 3:
							result = c1.getPublisherHouse().getName().compareToIgnoreCase(
									c2.getPublisherHouse().getName())
									* (param.sSortDir[i].equals("asc") ? -1 : 1);
							break;
						case 4:
							result = c1.getDatePublication()
									.toString()
									.compareToIgnoreCase(
											c2.getDatePublication().toString())
									* (param.sSortDir[i].equals("asc") ? -1 : 1);
							break;	
						case 5:
							result = c1.getFileType().compareToIgnoreCase(
									c2.getFileType())
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

		if (book.size() < param.iDisplayStart + param.iDisplayLength)
			book = book.subList(param.iDisplayStart, book.size());
		else
			book = book.subList(param.iDisplayStart, param.iDisplayStart
					+ param.iDisplayLength);

		try {
			JSONObject jsonResponse = new JSONObject();

			jsonResponse.put("sEcho", sEcho);
			jsonResponse.put("iTotalRecords", iTotalRecords);
			jsonResponse.put("iTotalDisplayRecords", iTotalDisplayRecords);

			for (Books c : book) {
				JSONArray row = new JSONArray();
				row.put(c.getId()).put(c.getTitle())
						.put(c.getFullname()).put(c.getPublisherHouse().getName())
						.put(c.getDatePublication()).put(c.getFileType());
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
	
	@RequestMapping(value = "/downloadBook", method = RequestMethod.GET)
	public void downloadBook(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int id = ServletRequestUtils.getRequiredIntParameter(request, "id");
		/*ObjectMapper mapper = new ObjectMapper();
		Books requesValue;
		requesValue = mapper.readValue(json, Books.class);*/
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"web-library-dao.xml");
		BooksJDBCTemplate booksJDBCTemplate = (BooksJDBCTemplate) context
				.getBean("booksJDBCTemplate");
		Books book = booksJDBCTemplate.getBook(id);
		File bookDownload = new File(book.getFilePath());
		InputStream  inputStream = null;
		//OutputStream outStream = null;

		try {
			inputStream = new FileInputStream(bookDownload);
						
			response.setHeader("Content-Disposition", "attachment; filename=\"" + bookDownload.getName() + "\"");
					
			
			FileCopyUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream)
					inputStream.close();
				if (null != inputStream)
					response.getOutputStream().close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
}
