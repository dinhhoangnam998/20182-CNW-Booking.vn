package webtech.gr14.controller.host.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.service.host.manage.HotelS;

@Controller("hostManageHotelC")
@RequestMapping("/host/manage/hotels")
public class HotelC {

	@Autowired
	private HotelS hS;

	@GetMapping
	public String showHotels(Model model) {
		List<Hotel> hotels = hS.getHotels();
		model.addAttribute("hotels", hotels);
		return "";
	}

	@GetMapping("/add")
	public String addHotel(Model model) {
		model.addAttribute("hotel", new Hotel());
		return "";
	}

	@PostMapping("/add")
	public String addHotel(RedirectAttributes rdA, Hotel hotel) {
		if (hS.validateNewHotel(hotel)) {
			hS.createNewHotel(hotel);
			rdA.addFlashAttribute("successMsg", hS.getAddSuccessMsg());
			return "";
		} else {
			rdA.addFlashAttribute("errMsgs", hS.getAddErrorMsgs());
			return "";
		}
	}

	@GetMapping("/{hid}")
	public String showHotel(Model model, @PathVariable int hid) {
		model.addAttribute("hotel", hS.hR.getOne(hid));
		return "";
	}

	@GetMapping("/{hid}/edit")
	public String editHotel(Model model, @PathVariable int hid) {
		model.addAttribute("hotel", hS.hR.getOne(hid));
		return "";
	}

	@PostMapping("{hid}/edit")
	public String editHotel(RedirectAttributes rdA, Hotel hotel) {
		if (hS.validateModifyHotel(hotel)) {
			hS.saveChange(hotel);
			rdA.addFlashAttribute("successMsg", hS.getEditSuccessMsg());
			return "";
		} else {
			rdA.addFlashAttribute("errMsgs", hS.getEditErrorMsgs());
			return "";
		}
	}

}
