package webtech.gr14.controller.host.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.model.room.Room;
import webtech.gr14.service.host.manage.HotelS;

@Controller("hostManageHotelC")
@RequestMapping("/host/manage/hotels")
public class HotelC {

	@Autowired
	private HotelS hS;

	@GetMapping
	public String showHotels() {
		return "";
	}

	@GetMapping("/add")
	public String addHotel() {
		return "";
	}

	@PostMapping("/add")
	public String addHotel(Hotel hotel) {
		return "";
	}

	@GetMapping("/{hid}")
	public String showHotel() {
		return "";
	}

	@GetMapping("/{hid}/edit")
	public String editHotel() {
		return "";
	}

	@PostMapping("{hid}/edit")
	public String editHotel(Hotel hotel) {
		return "";
	}

	

}
