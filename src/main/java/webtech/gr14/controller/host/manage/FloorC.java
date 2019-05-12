package webtech.gr14.controller.host.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webtech.gr14.model.floor.Floor;
import webtech.gr14.service.host.manage.FloorS;

@Controller
@RequestMapping("/host/manage/hotels/{hid}/")
public class FloorC {

	@Autowired
	private FloorS fS;

	@GetMapping("/floors")
	public String showFloors(Model model, @PathVariable int hid) {
		model.addAttribute("floors", fS.getFloors(hid));
		model.addAttribute("hid", hid);
		return "/host/manage/floor/list";
	}

	@GetMapping("/floors/add")
	public String addFloor(Model model, @PathVariable int hid) {
		model.addAttribute("floor", new Floor());
		model.addAttribute("hid", hid);
		return "/host/manage/floor/add";
	}

	@PostMapping("/floors/add")
	public String addFloor(RedirectAttributes rdA, Floor floor, @PathVariable int hid) {
		if (fS.validateNewFloor(floor, hid)) {
			fS.createNewFloor(floor, hid);
			rdA.addFlashAttribute("successMsg", fS.getCreateSuccessMsg());
			return "redirect:/host/manage/hotels/" + hid + "/floors";
		} else {
			rdA.addFlashAttribute("errMsgs", fS.getCreateErrorMsgs());
			return "redirect:/host/manage/hotels/" + hid + "/floors/add";
		}
	}

	@GetMapping("/floors/{fid}/edit")
	public String editFloor(Model model, @PathVariable int fid, @PathVariable int hid) {
		model.addAttribute("floor", fS.fR.getOne(fid));
		model.addAttribute("hid", hid);
		return "/host/manage/floor/edit";
	}

	@PostMapping("/floors/{fid}/edit")
	public String editFloor(RedirectAttributes rdA, Floor floor, @PathVariable int hid) {
		if (fS.validateModifyFloor(floor, hid)) {
			fS.saveChange(floor);
			rdA.addFlashAttribute("successMsg", fS.getEditSuccessMsg());
			return "redirect:/host/manage/hotels/" + hid + "/floors";
		} else {
			rdA.addFlashAttribute("errMsgs", fS.getEditErrorMsgs());
			System.out.println("--------------> errrrrrrrrrrrrrrrror");
			return "redirect:/host/manage/hotels/" + hid + "/floors/" + floor.getId() + "/edit";
		}
	}
}
