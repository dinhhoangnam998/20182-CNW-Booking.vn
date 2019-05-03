package webtech.gr14.controller.admin.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import webtech.gr14.model.ReserveOrder;
import webtech.gr14.service.admin.manage.GuestS;

@Controller
@RequestMapping("/admin/manage")
public class GuestC {

	@Autowired
	private GuestS gS;

	@GetMapping("/list")
	public String show(Model model, @RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "10") int pS) {
		List<ReserveOrder> reserveOrder = gS.getReserveOrderGuestOneStart(p, pS);
		List<Integer> pageList = gS.getPageList(p, pS);
		return "/admin/manage/guest";
	}
}
