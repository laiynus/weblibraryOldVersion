package by.khrapovitsky.controller;



import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.khrapovitsky.dao.UsersJDBCTemplate;
import by.khrapovitsky.model.Users;

@Controller
@RequestMapping("/myprofile")
public class UserProfileController {

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView profileInfo() {

		ModelAndView model = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if ((auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			return new ModelAndView("forward:/sign-in");
		}
		UserDetails userDetails = (UserDetails) SecurityContextHolder
				.getContext().getAuthentication().getPrincipal();
		userDetails.getUsername();
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"web-library-dao.xml");
		UsersJDBCTemplate usersJDBCTemplate = (UsersJDBCTemplate) context
				.getBean("usersJDBCTemplate");
		Users user = usersJDBCTemplate.getUser(userDetails.getUsername());
		model.addObject("user", user);
		model.setViewName("/main/myprofile");
		return model;

	}

	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView editProfile(@ModelAttribute("user") Users user) {

		ModelAndView model = new ModelAndView();
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"web-library-dao.xml");
		UsersJDBCTemplate usersJDBCTemplate = (UsersJDBCTemplate) context
				.getBean("usersJDBCTemplate");

		usersJDBCTemplate.update(user.getLogin(), user.getLastName(),
				user.getFirstName(), user.getPatronymic(), 
				user.getFaculty(), user.getChair(), user.getSpecialty());
		model.addObject("msg", "You've been change information successfully!");
		model.addObject("user", user);
		model.setViewName("/main/userprofile");

		return model;

	}
}
