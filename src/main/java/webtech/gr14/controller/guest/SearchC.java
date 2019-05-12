package webtech.gr14.controller.guest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.service.guest.SearchS;

@Controller
@RequestMapping("/guest")
public class SearchC {
	@Autowired
	private SearchS sS;

	@PostMapping("/search-hotels")
	public String searchHotels(HttpSession ss, Model model, @RequestParam String address,
			@RequestParam String dateRange, @RequestParam int numOfRoom, @RequestParam int numOfPeople) {
		List<Hotel> hotels = sS.getSearchResults(ss, address, dateRange, numOfRoom, numOfPeople);
		model.addAttribute("hotels", hotels);
		return "/guest/search/search-results";
	}
}
