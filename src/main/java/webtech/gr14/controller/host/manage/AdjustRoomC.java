package webtech.gr14.controller.host.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webtech.gr14.service.host.manage.AdjustRoomS;

@Controller
public class AdjustRoomC {
	@Autowired
	private AdjustRoomS arS;
}
