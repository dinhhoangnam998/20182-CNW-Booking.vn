package webtech.gr14.controller.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import webtech.gr14.model.floor.Room;
import webtech.gr14.repository.floor.RoomR;
import webtech.gr14.util.date.DateCommonUtil;

@Controller
public class TestC {

	@Autowired
	private RoomR rR;

	@GetMapping("/test")
	public String Test(HttpSession ss) {
		String dateRange = (String) ss.getAttribute("dateRange");
		List<Date> dateList = DateCommonUtil.getDatesFromStringDateRange(dateRange);

		Room r = rR.getOne(2);
		List<Date> openDates = r.getRemainOpenDates();

		for (Date date : dateList) {
			System.out.println(date);
		}

		for (Date date : openDates) {
			System.out.println(date);
		}

		List<Date> toRemove = new ArrayList<Date>();
		for (Date date : dateList) {
			for (Date date2 : openDates) {
				if (date2.getTime() == date.getTime()) {
					toRemove.add(date2);
				}
			}
		}

		System.out.println("before: " + openDates.size());

		openDates.removeAll(toRemove);

		System.out.println("after: " + openDates.size());

		return "redirect:/guest/home";
	}

}
