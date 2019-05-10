package webtech.gr14.controller.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.service.guest.HistoryS;

@Controller
@RequestMapping("/guest")
public class HistoryC {

	@Autowired
	private HistoryS hS;
	
	@GetMapping("/reserve-history")
	public String history() {
		return "";
	}
}
