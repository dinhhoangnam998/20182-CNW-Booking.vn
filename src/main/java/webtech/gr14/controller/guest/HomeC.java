package webtech.gr14.controller.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.service.guest.HomeS;

@Controller
@RequestMapping("/user")
public class HomeC {

	@Autowired
	private HomeS hS;
	
	@GetMapping("/home")
	public String home() {
		return "";
	}
	
	
	
	
}
