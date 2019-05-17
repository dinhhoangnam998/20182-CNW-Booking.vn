package webtech.gr14.controller.host.manage;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
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
		return "/host/manage/hotel/list";
	}

	@GetMapping("/add")
	public String addHotel(Model model) {
		model.addAttribute("hotel", new Hotel());
		model.addAttribute("communes", hS.getAllCommune());
		model.addAttribute("districts", hS.getAllDistrict());
		model.addAttribute("provinces", hS.getAllProvince());
		return "/host/manage/hotel/add";
	}

	@PostMapping(value = "/add", consumes = { "multipart/form-data" })
	public String addHotel(RedirectAttributes rdA, Hotel hotel, MultipartFile img, MultipartFile[] imgs,
			MultipartFile[] thumbs) {
		if (hS.validateNewHotel(hotel)) {
			hS.createNewHotel(hotel, img, imgs, thumbs);
			rdA.addFlashAttribute("successMsg", hS.getAddSuccessMsg());
			return "redirect:/host/manage/hotels";
		} else {
			rdA.addFlashAttribute("errMsgs", hS.getAddErrorMsgs());
			return "redirect:/host/manage/hotels/add";
		}
	}

	@GetMapping("/{hid}")
	public String showHotel(HttpSession ss, Model model, @PathVariable int hid) {
		ss.setAttribute("hid", hid);
		model.addAttribute("hotel", hS.hR.getOne(hid));
		return "/host/manage/hotel/info";
	}

	@GetMapping("/{hid}/edit")
	public String editHotel(Model model, @PathVariable int hid) {
		model.addAttribute("hotel", hS.hR.getOne(hid));
		model.addAttribute("communes", hS.getAllCommune());
		model.addAttribute("districts", hS.getAllDistrict());
		model.addAttribute("provinces", hS.getAllProvince());
		return "/host/manage/hotel/edit";
	}

	@PostMapping(value = "{hid}/edit", consumes = { "multipart/form-data" })
	public String editHotel(RedirectAttributes rdA, Hotel hotel, MultipartFile img, MultipartFile[] imgs,
			MultipartFile[] thumbs) {
		if (hS.validateModifyHotel(hotel)) {
			hS.saveChange(hotel, img, imgs, thumbs);
			rdA.addFlashAttribute("successMsg", hS.getEditSuccessMsg());
			return "redirect:/host/manage/hotels/" + hotel.getId();
		} else {
			rdA.addFlashAttribute("errMsgs", hS.getEditErrorMsgs());
			return "redirect:/host/manage/hotels/" + hotel.getId() + "/edit";
		}
	}

}
