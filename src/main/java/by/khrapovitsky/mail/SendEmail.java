package by.khrapovitsky.mail;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import by.khrapovitsky.model.Email;
import by.khrapovitsky.properties.Configuration;

public class SendEmail {
	
	
	private JavaMailSender mailSender;
	
	public SendEmail(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	
	
	public String sendEmail(Email email,String action){
		try{
			@SuppressWarnings("resource")
			ApplicationContext context = new ClassPathXmlApplicationContext(
					"web-library-context.xml");
			Configuration config = (Configuration) context
					.getBean("feedbackweblibrary");
			String recipientAddress = config.getFeedbackweblibrary();
			SimpleMailMessage mail = new SimpleMailMessage();
			if(action=="handlingUser"){
				mail.setTo(recipientAddress);
				mail.setSubject(email.getSubject());
				String fullMessage = "Hello, Roman this message from user: " + email.getMessage() + "\nUser login: " + email.getName() + "\nUser email: " + email.getEmail();
				mail.setText(fullMessage);
				mailSender.send(mail);
				return "Your email was successfully sent";
			}if(action=="newUser"){
				mail.setTo(recipientAddress);
				mail.setSubject(email.getSubject());
				String fullMessage = "Hello,Roman this is new user: " + email.getName() + "\nUser email: " + email.getEmail();
				mail.setText(fullMessage);
				mailSender.send(mail);
				return "New user";
			}if(action=="resetPassord"){
				mail.setTo(email.getEmail());
				mail.setSubject(email.getSubject());
				mail.setText(email.getMessage());
				
				mailSender.send(mail);
				return "Request to reset your password was sent on your email.";
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
