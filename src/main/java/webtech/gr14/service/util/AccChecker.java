package webtech.gr14.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.Acc;
import webtech.gr14.repository.AccR;

@Service
public class AccChecker {

	@Autowired
	private AccR aR;

	private Pattern pattern;
	private Matcher matcher;
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
			+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public boolean checkUsername(String username) {
		Acc acc = aR.findByUsername(username);
		return (acc == null);
	}

	public boolean checkPassword(String password, String confirmPassword) {
		return (password.equals(confirmPassword) && password.length() >= 8);
	}

	public boolean checkEmail(String email) {
		pattern = Pattern.compile(EMAIL_PATTERN);
		matcher = pattern.matcher(email);
		return (matcher.matches());
	}

	public boolean checkPhone(String phone) {
		if (phone.equals("")) {
			return true;
		} else {
			pattern = Pattern.compile("\\d{3}\\d{7}");
			matcher = pattern.matcher(phone);
			return (matcher.matches());
		}

	}

	public boolean checkAddress(String address) {
		// TODO Auto-generated method stub
		return true;
	}
}
