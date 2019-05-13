package webtech.gr14.controller.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import webtech.gr14.model.Acc;
import webtech.gr14.model.address.Commune;
import webtech.gr14.model.address.District;
import webtech.gr14.model.address.Province;
import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.repository.AccR;
import webtech.gr14.repository.address.CommuneR;
import webtech.gr14.repository.address.DistrictR;
import webtech.gr14.repository.address.ProvinceR;
import webtech.gr14.repository.hotel.HotelR;
import webtech.gr14.util.enums.AccRole;

@Controller
public class SeedDataC {

	@Autowired
	private AccR aR;
	
	@Autowired
	private ProvinceR pR;
	
	@Autowired
	private DistrictR dR;
	
	@Autowired
	private CommuneR cR;
	
	@Autowired
	private HotelR hR;

	@GetMapping("/seed-role")
	public String seedRole() {
		Acc admin = aR.findByUsername("admin");
		if (admin != null) {
			admin.setRole(AccRole.ADMIN);
		}
		aR.save(admin);
		return "redirect:/guest/home";
	}

	@GetMapping("/seed-address")
	public String seedAddr() {
		Province p = new Province(1, "Bac Ninh");
		pR.save(p);
		District d = new District(1, "Tu Son", p);
		dR.save(d);
		Commune c1 = new Commune(1, "Xa Huong Mac", d);
		cR.save(c1);
		
		List<Hotel> hotels = hR.findAll();
		for(Hotel hotel : hotels) {
			hotel.setCommune(c1);
			hR.save(hotel);
		}
		return "redirect:/guest/home";
	}
}
