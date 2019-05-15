package webtech.gr14.controller.guest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.service.guest.LocationS;

@Controller("guestLocationC")
public class LocationC {
	
	@Autowired
	public LocationS lS;

	@GetMapping("/hotels")
	public String searchHotelInProvince(Model model, @RequestParam int provinceId) {
		List<Hotel> hotels = lS.getHotelInProvince(provinceId);
		model.addAttribute("hotels", hotels);
		return "/guest/location/hotel-in-province";
	}
	
	@ResponseBody
	@GetMapping("/locations/{lid}/toggle")
	public String toggle(@PathVariable  int lid) {
		lS.toggle(lid);
		return "";
	}

}
