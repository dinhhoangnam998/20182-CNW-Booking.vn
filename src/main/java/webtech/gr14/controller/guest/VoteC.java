package webtech.gr14.controller.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import webtech.gr14.service.guest.VoteS;

@Controller
public class VoteC {

	@Autowired
	private VoteS vS;
	
	@ResponseBody
	@GetMapping("/guest/vote/{roid}")
	public String voteByGuest(@PathVariable int roid, @RequestParam int value) {
		vS.setVoteByGuest(roid, value);
		return "ok";
	}
	
}
