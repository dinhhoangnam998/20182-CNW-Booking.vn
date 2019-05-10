package webtech.gr14.controller.host.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import webtech.gr14.service.host.manage.AdjustRoomS;

@Controller
@RequestMapping("/host/manage/hotels/{hid}/rooms/adjust")
public class AdjustRoomC {

	@Autowired
	private AdjustRoomS arS;

	@GetMapping
	public String list(Model model, @PathVariable int hid) {
		model.addAttribute("rooms", arS.getRooms(hid));
		return "";
	}

	@ResponseBody
	@GetMapping("/{rid}")
	public String adjustRoom(@RequestParam String dates, @RequestParam int price, @PathVariable int rid) {
		arS.adjust(rid, dates, price);
		return "";
	}

	@ResponseBody
	@GetMapping("/{rid}/off")
	public String turnOff(@PathVariable int rid) {
		arS.turnOff(rid);
		return "";
	}

	@ResponseBody
	@GetMapping("/{rid}/on")
	public String turnOn(@PathVariable int rid) {
		arS.turnOn(rid);
		return "";
	}

}
