package webtech.gr14.controller.admin.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.service.admin.manage.SubmitHandleS;

@Controller
@RequestMapping("/admin/manage/submits")
public class SubmitHandleC {

	@Autowired
	private SubmitHandleS shS;
	
	@GetMapping
	public String showSubmits() {
		return "";
	}
	
	@GetMapping("/{sid}")
	public String showSubmit() {
		return "";
	}
	
	@GetMapping("/{sid}/new")
	public String markNew() {
		return "";
	}
	
	@GetMapping("/{sid}/processing")
	public String markProcessing() {
		return "";
	}
	
	@GetMapping("/{sid}/approval")
	public String markApprovaled() {
		return "";
	}
}
