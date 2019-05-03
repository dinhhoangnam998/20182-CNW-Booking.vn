package webtech.gr14.controller.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webtech.gr14.service.guest.HomeS;

@Controller
public class HomeC {

	@Autowired
	private HomeS hS;
}
