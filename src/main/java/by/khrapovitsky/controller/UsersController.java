package by.khrapovitsky.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;






import by.khrapovitsky.dao.UsersJDBCTemplate;
import by.khrapovitsky.dao.UsersRoleJDBCTemplate;
import by.khrapovitsky.datatable.DataTablesParamUtility;
import by.khrapovitsky.datatable.JQueryDataTableParamModel;
import by.khrapovitsky.model.Users;


@Controller
@RequestMapping("/users")
public class UsersController {
	
	@RequestMapping
	public ModelAndView users() {

		ModelAndView model = new ModelAndView();
		model.setViewName("admin_pages/users/users");
		return model;

	}
	
	
	
	@RequestMapping(value = "/springPaginationDataTables.web", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody void springPaginationDataTables(
			HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		
		final JQueryDataTableParamModel param = DataTablesParamUtility.getParam(request);
		
		String sEcho = param.sEcho;
		int iTotalRecords; // total number of records (unfiltered)
		int iTotalDisplayRecords;//value will be set when code filters companies by keyword
		JsonArray data = new JsonArray(); //data that will be shown in the table

		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"web-library-dao.xml");
		UsersJDBCTemplate usersJDBCTemplate = (UsersJDBCTemplate) context
				.getBean("usersJDBCTemplate");
		List<Users> users = usersJDBCTemplate.listUsers();
		
		iTotalRecords = users.size();
		List<Users> user = new LinkedList<Users>();
		for(Users c : users){
			//Cannot search by column 0 (id)
			if(param.bSearchable[0] &&
				c.getLogin().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[2] &&
				c.getEmail().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[2] &&
				c.getDateRegistration().toString().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[3] &&
				c.getFirstName().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[4] &&
				c.getLastName().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[5] &&
				c.getPatronymic().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[6] &&
				c.getFaculty().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[7] &&
				c.getChair().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[8] &&
				c.getSpecialty().toLowerCase().contains(param.sSearchKeyword.toLowerCase())
				||
				param.bSearchable[9] &&
				Integer.toString(c.getEnabled()).toLowerCase()
				.contains(param.sSearchKeyword.toLowerCase()))
				
			{
				user.add(c); // Add a company that matches search criterion
			}
		}
		iTotalDisplayRecords = user.size();//Number of companies that matches search criterion should be returned
		
		
		Collections.sort(user, new Comparator<Users>(){
			@Override
			public int compare(Users c1, Users c2) {
				int result = 0;
				for(int i=0; i<param.iSortingCols; i++){
					int sortBy = param.iSortCol[i];
					if(param.bSortable[sortBy]){
						switch(sortBy){
							case 0:
								result =	c1.getLogin().compareToIgnoreCase(c2.getLogin()) * 
								(param.sSortDir[i].equals("asc") ? -1 : 1);
							break;
							case 1:
								result =	c1.getEmail().compareToIgnoreCase(c2.getEmail()) * 
								(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 2:
								result =	c1.getDateRegistration().toString().compareToIgnoreCase(c2.getDateRegistration().toString()) * 
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 3:
								result =	c1.getFirstName().compareToIgnoreCase(c2.getFirstName()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 4:
								result =	c1.getLastName().compareToIgnoreCase(c2.getLastName()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 5:
								result =	c1.getPatronymic().compareToIgnoreCase(c2.getPatronymic()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 6:
								result =	c1.getFaculty().compareToIgnoreCase(c2.getFaculty()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 7:
								result =	c1.getChair().compareToIgnoreCase(c2.getChair()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 8:
								result =	c1.getSpecialty().compareToIgnoreCase(c2.getSpecialty()) *
											(param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
							case 9:
								result = Integer.toString(c1.getEnabled()).compareToIgnoreCase(
										Integer.toString(c2.getEnabled()))
										* (param.sSortDir[i].equals("asc") ? -1 : 1);
								break;
						}
					}
					if(result!=0)
						return result;
					else
						continue;
				}
				return result;
			}
		});
		
		if(user.size()< param.iDisplayStart + param.iDisplayLength)
			user = user.subList(param.iDisplayStart, user.size());
		else
			user = user.subList(param.iDisplayStart, param.iDisplayStart + param.iDisplayLength);
	
		
		try {
			JsonObject jsonResponse = new JsonObject();
			
			jsonResponse.addProperty("sEcho", sEcho);
			jsonResponse.addProperty("iTotalRecords", iTotalRecords);
			jsonResponse.addProperty("iTotalDisplayRecords", iTotalDisplayRecords);
			
			for(Users c : user){
				JsonArray row = new JsonArray();
				row.add(new JsonPrimitive(c.getLogin()));
				row.add(new JsonPrimitive(c.getEmail()));
				row.add(new JsonPrimitive(c.getDateRegistration().toString()));
				row.add(new JsonPrimitive(c.getFirstName()));
				row.add(new JsonPrimitive(c.getLastName()));
				row.add(new JsonPrimitive(c.getPatronymic()));
				row.add(new JsonPrimitive(c.getFaculty()));
				row.add(new JsonPrimitive(c.getChair()));
				row.add(new JsonPrimitive(c.getSpecialty()));
				row.add(new JsonPrimitive(c.getEnabled()));
				data.add(row);
			}
			jsonResponse.add("aaData", data);
			
			response.setContentType("application/json");
			response.getWriter().print(jsonResponse.toString());
		} catch (JSONException e) {
			e.printStackTrace();
			response.setContentType("text/html");
			response.getWriter().print(e.getMessage());
		}
	
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody void deleteUser(
			HttpServletRequest request, HttpServletResponse response) throws IOException{
			String login = request.getParameter("id").toString();
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"web-library-dao.xml");
			UsersJDBCTemplate usersJDBCTemplate = (UsersJDBCTemplate) context
					.getBean("usersJDBCTemplate");
			UsersRoleJDBCTemplate usersRoleJDBCTemplate = (UsersRoleJDBCTemplate) context
					.getBean("usersRoleJDBCTemplate");
			
			if((usersJDBCTemplate.getUser(login)!=null) || (usersRoleJDBCTemplate.getUserRole(login).getRole().equals("ROLE_ADMIN"))){
					response.getWriter().print("User not found or this user is admin!");
					return;
			}else{
					usersJDBCTemplate.delete(login);
					response.getWriter().print("User is successfully removed!");
					return;
			}
	}
	@RequestMapping(value = "/activateUser", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody void activateUser(
			HttpServletRequest request, HttpServletResponse response) throws IOException{
			String login = request.getParameter("id").toString();
			int enabled = Integer.parseInt(request.getParameter("value"));
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"web-library-dao.xml");
			UsersJDBCTemplate usersJDBCTemplate = (UsersJDBCTemplate) context
					.getBean("usersJDBCTemplate");
			Users user = usersJDBCTemplate.getUser(login);
			UsersRoleJDBCTemplate usersRoleJDBCTemplate = (UsersRoleJDBCTemplate) context
					.getBean("usersRoleJDBCTemplate");
			if((user==null) || (usersRoleJDBCTemplate.getUserRole(login).getRole().equals("ROLE_ADMIN"))){
					response.getWriter().print("User not found or this user is admin!");
					return;
			}else{
				if(enabled==0){
					usersJDBCTemplate.activateUser(login, enabled);;
					response.getWriter().print("User is successfully deactivate!");
					return;
				}if(enabled==1)
					usersJDBCTemplate.activateUser(login, enabled);;
					response.getWriter().print("User is successfully activate!");
					return;
			}
	}
}
