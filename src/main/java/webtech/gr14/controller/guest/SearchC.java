package webtech.gr14.controller.guest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import webtech.gr14.model.address.District;
import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.service.guest.SearchS;

@Controller
@RequestMapping("/guest")
public class SearchC {
	@Autowired
	private SearchS sS;

	@ResponseBody
	@GetMapping("/get-recomment")
	public List<District> getRecomment(@RequestParam String querry) {
		List<District> result = sS.getRecomment(querry);
		return result;
	}

	@PostMapping("/search-hotels")
	public String searchHotels(HttpSession ss, Model model, @RequestParam String address,
			@RequestParam String dateRange, @RequestParam(required = false, defaultValue = "1") int numOfRoom,
			@RequestParam(required = false, defaultValue = "1") int numOfPeople) {
		List<Hotel> hotels = sS.getSearchResults(ss, address, dateRange, numOfRoom, numOfPeople);
		model.addAttribute("hotels", hotels);
		model.addAttribute("numOfResult", hotels.size());
		model.addAttribute("address", address);
		return "/guest/search/search-results";
	}
	

	
}
