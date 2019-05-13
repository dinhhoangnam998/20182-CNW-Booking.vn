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
		Province p1 = new Province();
		p1.setName("Bắc Ninh");
		pR.save(p1);

		Province p2 = new Province();
		p2.setName("Hà Nội");
		pR.save(p2);

		Province p3 = new Province();
		p3.setName("Hồ Chí Minh");
		pR.save(p3);

		Province p4 = new Province();
		p4.setName("Đà Nẵng");
		pR.save(p4);

		Province p5 = new Province();
		p5.setName("Khánh Hòa");
		pR.save(p5);

		District d1 = new District();
		d1.setName("Từ Sơn");
		d1.setProvince(p1);
		dR.save(d1);

		District d2 = new District();
		d2.setName("Quế Võ");
		d2.setProvince(p1);
		dR.save(d2);

		District d3 = new District();
		d3.setName("Hoàn Kiếm");
		d3.setProvince(p2);
		dR.save(d3);

		District d4 = new District();
		d4.setName("Ba Đình");
		d4.setProvince(p2);
		dR.save(d4);

		District d5 = new District();
		d5.setName("Quận 1");
		d5.setProvince(p3);
		dR.save(d5);

		District d6 = new District();
		d6.setName("Quận 2");
		d6.setProvince(p3);
		dR.save(d6);

		// District d7 = new District();
		// d7.setName("Từ Sơn");
		// d7.setProvince(p1);
		// dR.save(d7);

		// District d8 = new District();
		// d8.setName("Từ Sơn");
		// d8.setProvince(p1);
		// dR.save(d8);

		// District d9 = new District();
		// d9.setName("Từ Sơn");
		// d9.setProvince(p1);
		// dR.save(d9);

		// District d10 = new District();
		// d10.setName("Từ Sơn");
		// d10.setProvince(p1);
		// dR.save(d10);

		Commune c1 = new Commune();
		c1.setName("Xã Hương Mạc");
		c1.setDistrcit(d1);
		cR.save(c1);
		
		Commune c2 = new Commune();
		c2.setName("Xã Phù Khê");
		c1.setDistrcit(d1);
		cR.save(c2);
		

//		Commune c2 = new Commune();
//		c2.setName("Xã Hương Mạc");
//		c1.setDistrcit(d1);
//		cR.save(c2);
//		
//
//		Commune c2 = new Commune();
//		c2.setName("Xã Hương Mạc");
//		c1.setDistrcit(d1);
//		cR.save(c2);
//		
//
//		Commune c2 = new Commune();
//		c2.setName("Xã Hương Mạc");
//		c1.setDistrcit(d1);
//		cR.save(c2);
//		
//
//		Commune c2 = new Commune();
//		c2.setName("Xã Hương Mạc");
//		c1.setDistrcit(d1);
//		cR.save(c2);
		

		
		
		List<Hotel> hotels = hR.findAll();
		for (Hotel hotel : hotels) {
			
			hotel.setCommune(c1);
			hR.save(hotel);
		}
		return "redirect:/guest/home";
	}
}
