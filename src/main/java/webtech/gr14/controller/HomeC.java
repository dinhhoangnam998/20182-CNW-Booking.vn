package webtech.gr14.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webtech.gr14.service.HomeS;

@Controller
public class HomeC {

	@Autowired
	private HomeS hS;
}
