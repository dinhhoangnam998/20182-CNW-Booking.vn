package webtech.gr14.controller.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import webtech.gr14.model.Acc;
import webtech.gr14.model.address.Commune;
import webtech.gr14.model.address.District;
import webtech.gr14.model.address.Province;
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
		try {
			List<String> provinces = Files.readAllLines(Paths.get("src/main/resources/for-dev-only/addressTextDate/provinces.txt"));
			for (String p : provinces) {
				Province province = new Province();
				province.setName(p);
				pR.save(province);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			List<String> districts = Files.readAllLines(Paths.get("src/main/resources/for-dev-only/addressTextDate/districts.txt"));
			int i = 10;
			for (String d : districts) {
				i++;
				District dis = new District();
				dis.setName(d);
				dis.setProvince(pR.getOne(i / 10));
				dR.save(dis);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			List<String> communes = Files.readAllLines(Paths.get("src/main/resources/for-dev-only/addressTextDate/communes.txt"));
			int j = 1;
			for (String comm : communes) {
				j++;
				Commune commune = new Commune();
				commune.setName(comm);
				commune.setDistrcit(dR.getOne(j / 2));
				cR.save(commune);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return "redirect:/guest/home";
	}
}
