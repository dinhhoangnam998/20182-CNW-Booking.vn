package webtech.gr14.controller.host.statistic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webtech.gr14.service.host.statistic.ReserveOrderS;

@Controller("hostStatisticReserveOrderC")
public class ReserveOrderC {

	@Autowired
	private ReserveOrderS roS;
}
