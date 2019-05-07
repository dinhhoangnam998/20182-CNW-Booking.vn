package webtech.gr14.controller.host.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.service.host.manage.AdjustRoomS;

@Controller
@RequestMapping("/host/manage/hotels/{hid}/rooms/adjust/{rid}")
public class AdjustRoomC {
	@Autowired
	private AdjustRoomS arS;
	
	@GetMapping
	public String adjustRoom() {
		return "";
	}
	
	
}
