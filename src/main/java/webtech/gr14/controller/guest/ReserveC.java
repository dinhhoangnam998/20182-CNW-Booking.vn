package webtech.gr14.controller.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.service.guest.ReserveS;

@Controller
@RequestMapping("/user")
public class ReserveC {

	@Autowired
	private ReserveS rS;
	
	@PostMapping("/reserve")
	public String reserve() {
		return "";
	}
	
	@GetMapping("/checkout")
	public String checkout() {
		return "";
	}
	
	@PostMapping("/checkout")
	public String finalCheckout() {
		return "";
	}
}
