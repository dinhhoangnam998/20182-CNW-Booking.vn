package webtech.gr14.controller.acc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webtech.gr14.model.Acc;
import webtech.gr14.service.acc.ProfileS;

@Controller
@RequestMapping("/acc")
public class ProfileC {

	@Autowired
	private ProfileS pS;

	@GetMapping("/profiles/{aid}")
	public String profile(Model model, @PathVariable int aid) {
		model.addAttribute("acc", pS.getAccById(aid));
		return "acc/profile";
	}

	@RequestMapping(value = "/profiles/{aid}/change-avatar", method = RequestMethod.POST, consumes = { "multipart/form-data" })
	public String changeAvatar(@PathVariable int aid, @RequestParam MultipartFile file) {
//		User user = accountService.userJpa.getOne(aid);
//		user.setImgURL("/images/user/" + sfSvc.storageFile(file, "user", user.getUsername()));
//		accountService.userJpa.save(user);
		return "redirect:/profiles/" + aid;
	}

	@GetMapping("/profiles/{aid}/edit")
	public String editProfile(Model model, @PathVariable int aid) {
		model.addAttribute("acc", pS.getAccById(aid));
		return "/acc/edit-profile";
	}

	@PostMapping("/profiles/{aid}/edit")
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

	@GetMapping("/profiles/{aid}/change-password")
	public String changePassword(Model model, @PathVariable int aid) {
		model.addAttribute("acc", pS.getAccById(aid));
		return "/acc/change-password";
	}

	@PostMapping("/profiles/{aid}/change-password")
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
