package webtech.gr14.controller.common;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import webtech.gr14.model.Acc;
import webtech.gr14.service.acc.AccS;

@Controller
public class ErrorC {

	@Autowired
	private AccS aS;

	@GetMapping("/403")
	public String error403(HttpSession ss, Model model) {
		Acc acc = aS.getAcc();
		ss.setAttribute("acc", acc);
		return "/error/403";
	}

}
