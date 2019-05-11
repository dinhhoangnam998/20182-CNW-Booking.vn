package webtech.gr14.controller.host.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
		model.addAttribute("floor", afS.getFloors(hid));
		return "/host/manage/adjust/list";
	}

	// need replace this by AJAX
	// do not need, we use javascript is enough
//	@GetMapping("/{fild}")
//	public String showAdjustFloor(Model model, @PathVariable int fid) {
//		model.addAttribute("floor", afS.fR.getOne(fid));
//		return "";
//	}

	@ResponseBody
	@PostMapping("/{fid}")
	public String adjustRoom(@RequestParam String openDates, @RequestParam int price, @PathVariable int fid) {
		afS.adjust(fid, openDates, price);
		return "success";
	}
	
	@ResponseBody
	@GetMapping("/{fid}/toggle")
	public String toggle(@PathVariable int fid) {
		afS.toggle(fid);
		return "";
	}
	
//	may do not need
//	@ResponseBody
//	@GetMapping("/{fid}/off")
//	public String turnOff(@PathVariable int fid) {
//		afS.turnOff(fid);
//		return "";
//	}
//
//	@ResponseBody
//	@GetMapping("/{fid}/on")
//	public String turnOn(@PathVariable int fid) {
//		afS.turnOn(fid);
//		return "";
//	}
	
	

}
