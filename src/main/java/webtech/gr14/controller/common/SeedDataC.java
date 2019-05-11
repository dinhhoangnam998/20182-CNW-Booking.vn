package webtech.gr14.controller.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import webtech.gr14.model.Acc;
import webtech.gr14.repository.AccR;
import webtech.gr14.util.enums.AccRole;

@Controller
public class SeedDataC {

	@Autowired
	private AccR aR;

	@GetMapping("/seed-role")
	public String seedRole() {
		Acc admin = aR.findByUsername("admin");
		if (admin != null) {
			admin.setRole(AccRole.ADMIN);
		}
		Acc guest = aR.findByUsername("guest");
		if (guest != null) {
			guest.setRole(AccRole.GUEST);
		}
		Acc host = aR.findByUsername("host");
		if (host != null) {
			host.setRole(AccRole.HOST);
		}
		aR.save(admin);
		aR.save(host);
		aR.save(guest);
		return "redirect:/guest/home";
	}
}
