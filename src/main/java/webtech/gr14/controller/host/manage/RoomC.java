package webtech.gr14.controller.host.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webtech.gr14.model.room.Floor;
import webtech.gr14.service.host.manage.RoomS;

@Controller
@RequestMapping("/host/manage/hotels/{hid}/")
public class RoomC {

	@Autowired
	private RoomS rS;

	@GetMapping("/rooms")
	public String showRooms(Model model, @PathVariable int hid) {
		model.addAttribute("rooms", rS.getRooms(hid));
		return "";
	}

	@GetMapping("/rooms/add")
	public String addRoom(Model model, @PathVariable int hid) {
		model.addAttribute("room", new Floor());
		return "";
	}

	@PostMapping("/rooms/add")
	public String addRoom(RedirectAttributes rdA, Floor room, @PathVariable int hid) {
		if (rS.validateNewRoom(room, hid)) {
			rS.createNewRoom(room, hid);
			rdA.addFlashAttribute("successMsg", rS.getCreateSuccessMsg());
			return "";
		} else {
			rdA.addFlashAttribute("errMsgs", rS.getCreateErrorMsgs());
			return "";
		}
	}

	@GetMapping("/rooms/{rid}/edit")
	public String editRoom(Model model, @PathVariable int rid) {
		model.addAttribute("room", rS.rR.getOne(rid));
		return "";
	}

	@PostMapping("/rooms/{rid}/edit")
	public String editRoom(RedirectAttributes rdA, Floor room) {
		if(rS.validateModifyRoom(room)) {
			rS.saveChange(room);
			rdA.addFlashAttribute("successMsg", rS.getEditSuccessMsg());
			return "";
		} else {
			rdA.addFlashAttribute("errMsgs", rS.getEditErrorMsgs());
			return "";
		}
	}
}
