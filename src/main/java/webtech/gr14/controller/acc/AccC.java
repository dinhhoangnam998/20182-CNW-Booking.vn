package webtech.gr14.controller.acc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webtech.gr14.service.acc.AccS;

@Controller
public class AccC {

	@Autowired
	private AccS aS;
}
