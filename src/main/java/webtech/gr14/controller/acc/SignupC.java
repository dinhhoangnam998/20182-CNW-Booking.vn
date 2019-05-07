package webtech.gr14.controller.acc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webtech.gr14.model.Acc;
import webtech.gr14.service.acc.SignupS;

@Controller
@RequestMapping("/acc")
public class SignupC {
	
	@Autowired
	private SignupS sS;
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("acc", new Acc());
		return "acc/signup/signup";
	}
	
	@PostMapping("/signup")
	public String signup(RedirectAttributes rdA, Acc acc) {
		if(sS.checkSignupAccValid(acc)) {
			sS.createNewAcc(acc);
			rdA.addFlashAttribute("acc", acc);
			return "redirect:/acc/signup-success";
		} else {
			rdA.addFlashAttribute("msgs", sS.getSignUpErrorMessages());
			return "redirect:/acc/signup";
		}
	}
	
	@GetMapping("/signup-success")
	public String signupSuccess(RedirectAttributes redirAttr) {
		return "acc/signup/signup-success";
	}
}
