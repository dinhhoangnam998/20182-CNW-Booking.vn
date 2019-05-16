package webtech.gr14.controller.host.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import webtech.gr14.service.host.manage.AdjustFloorS;

@Controller
@RequestMapping("/host/manage/hotels/{hid}/floors/adjust")
public class AdjustFloorC {

	@Autowired
	private AdjustFloorS afS;

	@GetMapping
	public String list(Model model, @PathVariable int hid) {
		model.addAttribute("floors", afS.getFloors(hid));
		return "/host/manage/adjust/list";
	}

	@ResponseBody
	@GetMapping("/{fid}")
	public boolean adjustRoom(@RequestParam String openDates, @RequestParam int price, @PathVariable int fid) {
		afS.adjust(fid, openDates, price);
		return true;
	}

	@ResponseBody
	@GetMapping("/{fid}/toggle")
	public boolean toggle(@PathVariable int fid) {
		return afS.toggle(fid);
	}

}
