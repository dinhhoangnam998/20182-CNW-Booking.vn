package webtech.gr14.controller.guest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.service.guest.HomeS;

@Controller
@RequestMapping("/guest")
public class HomeC {

	@Autowired
	private HomeS hS;
	
	@GetMapping("/home")
	public String home(Model model) {
		List<Hotel> hotels = hS.getBestHotel();
		model.addAttribute("hotels", hotels);
		return "guest/home/home";
	}
	
	
	
	
}
