package webtech.gr14.controller.acc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.model.Acc;
import webtech.gr14.service.acc.LoginS;

@Controller
@RequestMapping("/acc")
public class LoginC {
	
	@Autowired
	private LoginS lS;

	@GetMapping("/login")
	public String login(Model model) {
		return "acc/login";
	}

	@GetMapping("/login-success")
	public String loginSuccess(HttpSession ss) {
		Acc acc = lS.getAcc();
		ss.setAttribute("acc", acc);
		return "redirect:/home";
	}
}
