package webtech.gr14.controller.guest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import webtech.gr14.model.article.Location;
import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.service.guest.HomeS;

@Controller
@RequestMapping("/guest")
public class HomeC {

	@Autowired
	private HomeS hS;

	@GetMapping("/home")
	public String home(Model model, HttpSession ss) {
		if (ss.getAttribute("firstTime") == null) {
			Date today = Calendar.getInstance().getTime();
			DateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
			String strDate = dateFormat.format(today);
			String dateRange = strDate + "-" + strDate;
			ss.setAttribute("dateRange", dateRange);
//			ss.setAttribute("address", address);
//			ss.setAttribute("numOfRoom", 1);
//			ss.setAttribute("numOfPeople", 1);
		}

		List<Hotel> hotels = hS.getBestHotel();
		model.addAttribute("hotels", hotels);
		List<Location> locations = hS.getLocation();
		model.addAttribute("locations", locations);
		return "guest/home/home";
	}

}
