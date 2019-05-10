package webtech.gr14.controller.acc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webtech.gr14.service.acc.LoginS;

@Controller
@RequestMapping("/acc")
public class LoginC {

	@Autowired
	private LoginS lS;
	
	@GetMapping("/login")
	public String login(Model model) {
		return "/acc/login/login";
	}

	@GetMapping("/login/success")
	public String loginSuccess(HttpSession ss) {
		lS.processWhenLoginSuccess(ss);
		return "redirect:/guest/home";
	}

	@GetMapping("/login/failure")
	public String loginFailure(RedirectAttributes rdA) {
		rdA.addFlashAttribute("msg", lS.getLoginFailureMsg());
		return "redirect:/guest/home";
	}
}
