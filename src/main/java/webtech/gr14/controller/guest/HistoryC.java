package webtech.gr14.controller.guest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.service.guest.HistoryS;

@Controller
@RequestMapping("/guest")
public class HistoryC {

	@Autowired
	private HistoryS hS;

	@GetMapping("/reserve-history")
	public String history(Model model) {
		List<ReserveOrder> ros = hS.getHistory();
		model.addAttribute("ros", ros);
		return "/guest/history/history";
	}
}
