package webtech.gr14.controller.admin.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.service.admin.manage.HostS;

@Controller
@RequestMapping("/admin/manage/hosts")
public class HostC {

	@Autowired
	private HostS hS;

	@GetMapping("/bad-transactions")
	public String showListBadTransaction() {
		return "";
	}

	@GetMapping("/{hid}")
	public String showHostInfo() {
		return "";
	}

	@GetMapping("/{hid}/warning")
	public String warning() {
		return "";
	}

	@GetMapping("/{hid}/unwarning")
	public String unwarning() {
		return "";
	}

	@GetMapping("/{hid}/block")
	public String block() {
		return "";
	}

	@GetMapping("/{hid}/unblock")
	public String unblock() {
		return "";
	}
}
