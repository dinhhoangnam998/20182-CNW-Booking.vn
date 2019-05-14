package webtech.gr14.controller.admin.manage;

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
import webtech.gr14.model.reserve.ReserveOrder;
import webtech.gr14.service.admin.manage.HotelS;

@Controller
@RequestMapping("/admin/manage/hotels")
public class HotelC {

	@Autowired
	private HotelS hS;

	@GetMapping("/bad-transactions")
	public String showListBadTransaction(Model model, @RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "10") int pS) {
		List<ReserveOrder> badTrans = hS.getBadTransactionsOfHotels(p, pS);
		model.addAttribute("trans", badTrans);
		List<Integer> pages = hS.getPageList(p, pS);
		model.addAttribute("pages", pages);
		return "/admin/manage/hotel/bad-transactions";
	}

	@GetMapping("/{hid}")
	public String showHotelInfo(Model model, @PathVariable int hid) {
		Hotel hotel = hS.hR.getOne(hid);
		model.addAttribute("hotel", hotel);
		List<ReserveOrder> recentTrans = hS.getSomeRecentTrans(hid);
		model.addAttribute("reserveOrders", recentTrans);
		return "/admin/manage/hotel/hotel-info";
	}

	@ResponseBody
	@GetMapping("/{hid}/get-more-transactions")
	public List<ReserveOrder> fetchTransactions(@PathVariable int hid, @RequestParam int ith) {
		List<ReserveOrder> ros = hS.getMoreTransactions(hid, ith);
		return ros;
	}

	@ResponseBody
	@GetMapping("/{hid}/warning")
	public String warning(@PathVariable int hid) {

		return hS.warning(hid);
	}

	@ResponseBody
	@GetMapping("/{hid}/unwarning")
	public String unwarning(@PathVariable int hid) {
		return hS.unwarning(hid);
	}

	@ResponseBody
	@GetMapping("/{hid}/block")
	public String block(@PathVariable int hid) {
		return hS.block(hid);
	}

	@ResponseBody
	@GetMapping("/{hid}/unblock")
	public String unblock(@PathVariable int hid) {
		return hS.unblock(hid);
	}
	
	@ResponseBody
	@GetMapping("/bad-transactions/{tid}/checked")
	public String checked(Model model, @PathVariable int tid) {
		return hS.markChecked(tid);
	}
}
