package webtech.gr14.controller.host.manage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webtech.gr14.service.host.manage.HotelS;

@Controller("hostManageHotelC")
public class HotelC {

	@Autowired
	private HotelS hS;
}
