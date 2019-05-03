package webtech.gr14.controller.admin.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webtech.gr14.service.admin.statistic.ReserveOrderS;

@Controller
public class ReserveOrderC {

	@Autowired
	private ReserveOrderS roS;
}
