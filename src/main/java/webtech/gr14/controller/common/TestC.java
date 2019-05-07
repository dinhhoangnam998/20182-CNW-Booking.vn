package webtech.gr14.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.model.hotel.HotelGeneralFacility;
import webtech.gr14.model.hotel.HotelService;
import webtech.gr14.model.hotel.HouseRule;
import webtech.gr14.repository.hotel.HotelR;

@Controller
public class TestC {

	@Autowired
	private HotelR hJpa;

	@GetMapping("/seed-data")
	public String seedData() {

		Hotel h = new Hotel();
		h.setName("myhotel");
		HotelService hotelService = new HotelService();
		hotelService.setRoomService(true);
		hotelService.setWakeup(true);
		h.setHotelService(hotelService);
		h.setHotelGeneralFacility(new HotelGeneralFacility());
		h.setHouseRule(new HouseRule());
		hJpa.save(h);
		return "redirect:/test";
	}

	@GetMapping("/test")
	@ResponseBody
	public String test() {
		Hotel h = hJpa.findByHotelService_Wakeup(true);

		return h.getName() + " Room Service: " + h.getHotelService().isRoomService() + " Laundry"
				+ h.getHotelService().isLaundry();
	}
}
