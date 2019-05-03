package webtech.gr14.controller.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webtech.gr14.service.guest.SearchS;

@Controller
public class SearchC {
	@Autowired
	private SearchS sS;
}
