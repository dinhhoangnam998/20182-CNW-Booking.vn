package webtech.gr14.controller.host.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.service.host.manage.BookingS;

@Controller("hostStatisticReserveOrderC")
@RequestMapping("/host/manage/hotels/{hid}/bookings")
public class BookingC {

	@Autowired
	private BookingS roS;
	
	@GetMapping
	public String list(Model model, @PathVariable int hid) {
		
		List<ReserveOrder> ros = roS.getReserveOrderOfHotel(hid);
		model.addAttribute("ros", ros);
		return "/host/manage/booking/list";
	}
}
