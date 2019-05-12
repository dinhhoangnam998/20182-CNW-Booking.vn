package webtech.gr14.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorC {

	@GetMapping("/403")
	public String error403(Model model) {
		return "/error/403";
	}

}
