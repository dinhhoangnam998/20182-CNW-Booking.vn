package webtech.gr14.controller.guest;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.model.floor.Floor;
import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.service.guest.HotelInfoS;

@Controller
@RequestMapping("/guest")
public class HotelInfoC {

	@Autowired
	private HotelInfoS hiS;

	@GetMapping("/hotels/{hid}")
	public String hotelInfo(Model model, HttpSession ss, @PathVariable int hid) {
		ss.setAttribute("hotelId", hid);
		Hotel hotel = hiS.hR.getOne(hid);
		List<Floor> floors = hiS.fR.findByHotel_IdAndDeleted(hid, false);
		List<Integer> remainRoomOfEachFloor = hiS.getRemainRoomOfEachFloor(ss, hid);
		
		model.addAttribute("hotel", hotel);
		model.addAttribute("floors", floors);
		model.addAttribute("remainRoomOfEachFloor", remainRoomOfEachFloor);
		
		return "/guest/hotel/hotel-info";
	}
}
