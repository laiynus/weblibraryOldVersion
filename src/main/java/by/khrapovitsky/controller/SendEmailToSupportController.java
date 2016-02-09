package by.khrapovitsky.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;

import by.khrapovitsky.dao.UsersJDBCTemplate;
import by.khrapovitsky.mail.SendEmail;
import by.khrapovitsky.model.Email;

@Controller
@RequestMapping("/mail")
public class SendEmailToSupportController {
	
	@Autowired
	private JavaMailSender mailSender;

	@RequestMapping(value = "/sendEmailToSupport", method = RequestMethod.POST)
	public @ResponseBody String sendEmailSupport(@RequestBody String json) {
		
		ObjectMapper mapper = new ObjectMapper();
		Email requesValue;
		Email email = new Email();
		try {
			requesValue = mapper.readValue(json, Email.class);
			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			String result = null;
			if (!(auth instanceof AnonymousAuthenticationToken)) {
				UserDetails userDetails = (UserDetails) SecurityContextHolder
						.getContext().getAuthentication().getPrincipal();
				email.setName(userDetails.getUsername());
				@SuppressWarnings("resource")
				ApplicationContext context = new ClassPathXmlApplicationContext(
						"web-library-dao.xml");
				UsersJDBCTemplate usersJDBCTemplate = (UsersJDBCTemplate) context
						.getBean("usersJDBCTemplate");
				email.setEmail(usersJDBCTemplate.getUser(
						userDetails.getUsername()).getEmail());
				email.setMessage(requesValue.getMessage());
				email.setSubject(requesValue.getSubject());
				result = new SendEmail(mailSender).sendEmail(email,
						"handlingUser");
				return result;
			} else {
				email.setMessage(requesValue.getMessage());
				email.setSubject(requesValue.getSubject());
				email.setEmail(requesValue.getEmail());
				email.setName(requesValue.getName());
				SendEmail sendMail = new SendEmail(mailSender);
				result = sendMail.sendEmail(email,"handlingUser");
				return result;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
