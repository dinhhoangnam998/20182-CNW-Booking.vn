package webtech.gr14.controller.admin.manage;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.service.admin.manage.SubmitHandleS;
import webtech.gr14.util.enums.ActiveState;
import webtech.gr14.util.enums.SubmitState;

@Controller
@RequestMapping("/admin/manage/submits")
public class SubmitHandleC {

	@Autowired
	private SubmitHandleS shS;

	@GetMapping
	public String showSubmits(Model model, @RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "10") int pS) {
		List<Hotel> submits = shS.getSubmits(p, pS);
		model.addAttribute("submits", submits);
		List<Integer> pages = shS.getPageList(p, pS);
		model.addAttribute("pages", pages);
		return "/admin/manage/submit/list";
	}

	@GetMapping("/{sid}")
	public String showSubmitInfo(Model model, @PathVariable int sid) {
		model.addAttribute("hotel", shS.hR.getOne(sid));
		return "/admin/manage/submit/hotel-info";
	}

	@ResponseBody
	@GetMapping("/{sid}/checked")
	public String markChecked(@PathVariable int sid) {
		Hotel hotel = shS.hR.getOne(sid);
		hotel.setSubmitState(SubmitState.PROCESSING);
		hotel.setHandleSubmitDate(new Date());
		shS.hR.save(hotel);
		return "PROCESSING";
	}

	@ResponseBody
	@GetMapping("/{sid}/approval")
	public String markApprovaled(@PathVariable int sid) {
		Hotel hotel = shS.hR.getOne(sid);
		hotel.setSubmitState(SubmitState.APPROVALED);
		hotel.setActiveState(ActiveState.ACTIVE);
		hotel.setHandleSubmitDate(new Date());
		shS.hR.save(hotel);
		return "APPROVALED";
	}

	@ResponseBody
	@GetMapping("/{sid}/decline")
	public String markDeclined(@PathVariable int sid) {
		Hotel hotel = shS.hR.getOne(sid);
		hotel.setSubmitState(SubmitState.DECLINED);
		hotel.setActiveState(ActiveState.BLOCKED);
		hotel.setHandleSubmitDate(new Date());
		shS.hR.save(hotel);
		return "DECLINED";
	}

}
