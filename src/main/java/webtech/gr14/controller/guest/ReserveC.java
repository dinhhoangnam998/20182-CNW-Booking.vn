package webtech.gr14.controller.guest;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.service.guest.ReserveS;

@Controller
@RequestMapping("/guest")
public class ReserveC {

	@Autowired
	private ReserveS rS;

	@PostMapping("/reserve")
	public String reserve(RedirectAttributes rdA, HttpSession ss, @RequestParam Integer[] floorsId,
			@RequestParam Integer[] numOfReserveRoom) {
		int reserveOrderId = rS.tempReserve(ss, floorsId, numOfReserveRoom);
		return "redirect:/guest/checkout/" + reserveOrderId;
	}

	@GetMapping("/checkout/{roid}")
	public String checkout(Model model, @PathVariable int roid) {
		ReserveOrder reserveOrder = rS.roR.getOne(roid);
		model.addAttribute("reserveOrder", reserveOrder);
		model.addAttribute("hotel", reserveOrder.getHotel());
		
		return "guest/reserve/checkout";
	}

	@PostMapping("/final-checkout/{roid}")
	public String finalCheckout(RedirectAttributes rdA, HttpSession ss, @PathVariable int roid, @RequestParam(required = false, defaultValue = "no note") String note) {
		if (rS.tryFinalCheckout(ss, roid, note)) {
			rdA.addFlashAttribute("msg", "success");
			return "redirect:/guest/reserve-history";
		} else {
			rdA.addFlashAttribute("mgs", "please refresh to update new info");
			return "redirect:/guest/checkout/" + roid;
		}
	}
}
