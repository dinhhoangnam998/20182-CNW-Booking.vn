package webtech.gr14.controller.admin.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webtech.gr14.service.admin.statistic.ReceiptS;

@Controller
public class ReceiptC {

	@Autowired
	private ReceiptS rS;
}
