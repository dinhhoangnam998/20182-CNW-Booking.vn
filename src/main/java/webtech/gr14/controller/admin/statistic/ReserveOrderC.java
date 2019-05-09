package webtech.gr14.controller.admin.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.service.admin.statistic.ReserveOrderS;

@Controller
@RequestMapping("reserve-orders")
public class ReserveOrderC {

	@Autowired
	private ReserveOrderS roS;
	
	// statistic by chart is the best idea
	@GetMapping
	public String showReserveOrders(Model model) {
		//
		return "";
	}
	
}
