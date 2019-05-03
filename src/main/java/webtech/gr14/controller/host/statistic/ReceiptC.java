package webtech.gr14.controller.host.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webtech.gr14.service.host.statistic.ReceiptS;

@Controller("hostStatisticReceiptC")
public class ReceiptC {

	@Autowired
	private ReceiptS rS;
}
