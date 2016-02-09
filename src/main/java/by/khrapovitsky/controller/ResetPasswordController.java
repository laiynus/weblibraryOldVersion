package by.khrapovitsky.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import by.khrapovitsky.dao.UsersJDBCTemplate;
import by.khrapovitsky.mail.SendEmail;
import by.khrapovitsky.model.Email;
import by.khrapovitsky.model.Users;

import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/resetpassword")
public class ResetPasswordController {
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	@RequestMapping(value = "/sendToEmailNewPassword", method = RequestMethod.POST)
	public @ResponseBody String sendEmailSupport(@RequestBody String json) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		Users requesValue;
		Users user = new Users();
		Email email = new Email();
		try {
			String result = null;
			requesValue = mapper.readValue(json, Users.class);
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"web-library-dao.xml");
			UsersJDBCTemplate usersJDBCTemplate = (UsersJDBCTemplate) context
					.getBean("usersJDBCTemplate");
			
		    if((user = usersJDBCTemplate.getUserByEmail(requesValue.getEmail()))!=null){
		    	String newPassword = generateRandomString();
		    	usersJDBCTemplate.resetPassword(user.getLogin(), newPassword);
		    	email.setEmail(user.getEmail());
		    	email.setMessage("Hello," + user.getLogin()  + "\nOn this email was sent request to reset password.\n" + "Here your new password: " + newPassword + "\nSupport Web-library");
		    	email.setSubject("Reset password on web-library");
		    	SendEmail sendMail = new SendEmail(mailSender);
				result = sendMail.sendEmail(email,"resetPassord");
				return result;
		    }else{
		    	return "This user not found or a server error occurred";
		    }
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String generateRandomString() throws Exception {

		StringBuffer buffer = new StringBuffer();
		String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
				
		int charactersLength = characters.length();

		for (int i = 0; i < 5; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}
	
}
