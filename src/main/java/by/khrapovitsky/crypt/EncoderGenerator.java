package by.khrapovitsky.crypt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncoderGenerator {

	public String passwordGenerator(String password) {

		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);

		return hashedPassword;

	}
}
