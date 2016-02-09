package by.khrapovitsky.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;





import by.khrapovitsky.dao.AuthorsJDBCTemplate;
import by.khrapovitsky.dao.AuthorsToBookJDBCTemplate;
import by.khrapovitsky.dao.BooksJDBCTemplate;
import by.khrapovitsky.dao.FileStatusJDBCTemplate;
import by.khrapovitsky.dao.FileTypesJDBCTemplate;
import by.khrapovitsky.dao.PublishHousesJDBCTemplate;
import by.khrapovitsky.datatable.DataTablesParamUtility;
import by.khrapovitsky.datatable.JQueryDataTableParamModel;
import by.khrapovitsky.ebookparser.Ebook;
import by.khrapovitsky.ebookparser.InstantParser;
import by.khrapovitsky.ebookparser.Parser;
import by.khrapovitsky.model.Books;
import by.khrapovitsky.upload.FileMeta;

@Controller
@RequestMapping("/adminbooks")
public class AdminBooksController {

	@RequestMapping
	public ModelAndView booksTable() {

		ModelAndView model = new ModelAndView();
		model.setViewName("admin_pages/books/adminbooks");
		return model;

	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody LinkedList<FileMeta> upload(
			MultipartHttpServletRequest request, HttpServletResponse response)
			throws IOException {

		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		FileMeta fileMeta = null;

		while (itr.hasNext()) {

			mpf = request.getFile(itr.next());

			if (files.size() >= 10)
				files.pop();

			fileMeta = new FileMeta();
			fileMeta.setFileName(mpf.getOriginalFilename());
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());

			try {
				String rootPath = System.getProperty("catalina.home");
				File dir = new File(rootPath + File.separator + "ebooks");
				if (!dir.exists())
					dir.mkdirs();
				String path = dir.getAbsolutePath() + File.separator
						+ mpf.getOriginalFilename();
				fileMeta.setBytes(mpf.getBytes());
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(path));
				Parser parser = new InstantParser();
				Ebook ebook = parser.parse(path);
				if (ebook.isOk) {
					@SuppressWarnings("resource")
					ApplicationContext daoContext = new ClassPathXmlApplicationContext(
							"web-library-dao.xml");
					BooksJDBCTemplate book = (BooksJDBCTemplate) daoContext
							.getBean("booksJDBCTemplate");
					PublishHousesJDBCTemplate publisher = (PublishHousesJDBCTemplate) daoContext
							.getBean("publishHouseJDBCTemplate");
					FileTypesJDBCTemplate fileType = (FileTypesJDBCTemplate) daoContext
							.getBean("fileTypeJDBCTemplate");
					FileStatusJDBCTemplate fileStatus = (FileStatusJDBCTemplate) daoContext
							.getBean("fileStatusJDBCTemplate");
					AuthorsJDBCTemplate authors = (AuthorsJDBCTemplate) daoContext
							.getBean("authorsJDBCTemplate");
					AuthorsToBookJDBCTemplate authorsToBooks = (AuthorsToBookJDBCTemplate) daoContext
							.getBean("authorsToBookJDBCTemplate");
					if (fileType.getFileType(ebook.getFormat().toString()) == null) {

						fileType.create(ebook.getFormat().toString(),
								fileMeta.getFileType());
					}

					Integer fk_publisher = null;
					java.util.Date currentDate = new java.util.Date();
					java.sql.Date sqlDate = null;
					if (ebook.getPublisher() != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
						java.util.Date date = sdf.parse(ebook.getPublisher()
								.getDateOfPublish());

						sqlDate = new Date(date.getTime());

						fk_publisher = publisher
								.create(ebook.getPublisher().getPublisher(),
										ebook.getPublisher().getCity());
					}

					int id_book = book.create(fk_publisher, ebook.getFormat()
							.toString(), sqlDate, ebook.getTitle(), ebook
							.getAnnotation(), path);
					int id_author = authors.create(ebook.authors.get(0).firstName, ebook.authors.get(0).lastName);
					authorsToBooks.create(id_book, id_author);

					fileStatus.create(id_book, SecurityContextHolder
							.getContext().getAuthentication().getName(),
							currentDate);
				}

			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}

			files.add(fileMeta);
		}

		return files;
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
		List<Books> books = booksJDBCTemplate.listBooks();

		iTotalRecords = books.size();
		List<Books> book = new LinkedList<Books>();
		for (Books c : books) {
			// Cannot search by column 0 (id)
			if (param.bSearchable[1]
					&& c.getTitle().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())
					|| param.bSearchable[2]
					&& c.getDatePublication().toString().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())
					|| param.bSearchable[3]
					&& c.getFilePath().toString().toLowerCase()
							.contains(param.sSearchKeyword.toLowerCase())
					|| param.bSearchable[4]
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
									.getDatePublication()
									.toString()
									.compareToIgnoreCase(
											c2.getDatePublication().toString())
									* (param.sSortDir[i].equals("asc") ? -1 : 1);
							break;
						case 3:
							result = c1.getFilePath().compareToIgnoreCase(
									c2.getFilePath())
									* (param.sSortDir[i].equals("asc") ? -1 : 1);
							break;
						case 4:
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
						.put(c.getDatePublication()).put(c.getFilePath())
						.put(c.getFileType());
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

	@RequestMapping(value = "/deleteBook", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody void deleteBook(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		int id_book = Integer.parseInt(request.getParameter("id"));
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"web-library-dao.xml");
		BooksJDBCTemplate booksJDBCTemplate = (BooksJDBCTemplate) context
				.getBean("booksJDBCTemplate");
		try {
			new File(booksJDBCTemplate.getBook(id_book).getFilePath()).delete();
			booksJDBCTemplate.delete(id_book);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(
					"File don't exist or it's already removed!");
			return;
		}
		response.getWriter().print("File is successfully removed!");
		return;
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
