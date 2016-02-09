package by.khrapovitsky.controller;

import java.util.Date;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import by.khrapovitsky.dao.UsersJDBCTemplate;
import by.khrapovitsky.model.Users;


@Controller
@RequestMapping("/sign-up")
public class SignUpController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView registration() {
		ModelAndView model = new ModelAndView();
		model.setViewName("sign-in-up/sign-up");
		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {

			/* The user is logged in :) */
			return new ModelAndView("forward:/home");
		}
		Users newUser = new Users();
		model.addObject("user", newUser);
		return model;
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView registerUser(@ModelAttribute("user") Users user) {
		ModelAndView model = new ModelAndView();
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"web-library-dao.xml");
		UsersJDBCTemplate usersJDBCTemplate = (UsersJDBCTemplate) context
				.getBean("usersJDBCTemplate");
		if(usersJDBCTemplate.getUser(user.getLogin())!=null){
			model.addObject("error","This user already exist!");
			model.setViewName("sign-in-up/sign-up");
		}else{
			Date dateRegistration = new Date();
			usersJDBCTemplate.registration(user.getLogin(), user.getPassword(), user.getEmail(), dateRegistration, 0);
			model.addObject("msg","You've been register successfully. Now you can log-in!");
			model.setViewName("sign-in-up/sign-in");
		}
		return model;

	}
}
