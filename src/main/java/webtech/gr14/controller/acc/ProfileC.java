package webtech.gr14.controller.acc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webtech.gr14.model.Acc;
import webtech.gr14.service.acc.ProfileS;

@Controller
@RequestMapping("/acc")
public class ProfileC {

	@Autowired
	private ProfileS pS;

	@GetMapping("/profiles/{id}")
	public String profile(Model model, @PathVariable int id) {
		model.addAttribute("acc", pS.getUserById(id));
		return "acc/profile";
	}

	@GetMapping("/profiles/{id}/edit")
	public String editProfile(Model model, @PathVariable int id) {
		model.addAttribute("acc", pS.getUserById(id));
		return "/acc/edit-profile";
	}

	@PostMapping("/profiles/{id}/edit")
	public String editProfile(RedirectAttributes rdA, Acc acc) {
		if (pS.checkModifyProfileValid(acc)) {
			pS.saveModifiedProfile(acc);
			rdA.addFlashAttribute("msg", "Change profile success!");
			return "/acc/profiles" + acc.getId();
		} else {
			rdA.addFlashAttribute("msgs", pS.getEditProfileErrorMessages());
			return "redirect:/acc/profiles/" + acc.getId() + "/edit";
		}
	}

	@GetMapping("/profiles/{id}/change-password")
	public String changePassword(Model model, @PathVariable int id) {
		model.addAttribute("acc", pS.getUserById(id));
		return "/acc/change-password";
	}

	@PostMapping("/profiles/{id}/change-password")
	public String changePassword(RedirectAttributes rdA, Acc acc) {
		if (pS.checkPasswordMatch(acc)) {
			pS.saveChangePassword(acc);
			rdA.addFlashAttribute("msg", "Change password success!");
			return "/acc/profiles/" + acc.getId();
		} else {
			rdA.addFlashAttribute("msgs", pS.getChangePasswordErrorMessage());
			return "/acc/profiles/" + acc.getId() + "/change-password";
		}
	}
}
