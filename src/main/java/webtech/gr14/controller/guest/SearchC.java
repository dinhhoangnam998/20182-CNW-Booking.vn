package webtech.gr14.controller.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.service.guest.SearchS;

@Controller
@RequestMapping("/user")
public class SearchC {
	@Autowired
	private SearchS sS;
	
	@GetMapping("/search-hotels")
	public String searchHotels() {
		return "";
	}
}
