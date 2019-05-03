package webtech.gr14.controller.admin.creation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webtech.gr14.service.admin.creation.SaleS;

@Controller
public class SaleC {
	@Autowired
	private SaleS sS;
}
