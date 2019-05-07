package webtech.gr14.config.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class SpringSecurityUtil {

	public static String getUsername() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((UserDetails) principal).getUsername();
	}
}
