package webtech.gr14.service.acc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import webtech.gr14.model.Acc;
import webtech.gr14.util.enums.AccRole;

@Service
public class LoginS {

	@Autowired
	private AccS aS;

	@Autowired
	private Environment env;

	public String getLoginFailureMsg() {
		return env.getProperty("msg.login.failure");
	}

	public void processWhenLoginSuccess(HttpSession ss) {
		Acc acc = aS.getAcc();
		ss.setAttribute("acc", acc);
	}

	public String getRedirectPage() {
		Acc acc = aS.getAcc();
		if (acc.getRole() == AccRole.ADMIN) {
			return "redirect:/admin/manage/submits";
		} else if (acc.getRole() == AccRole.HOST) {
			return "redirect:/host/manage/hotels";
		} else if (acc.getRole() == AccRole.GUEST) {
			return "redirect:/guest/home";
		} else {
			// redirect BLOCK page if you want
			return "redirect:/guest/home";
		}
	}

}
