package webtech.gr14.controller.admin.creation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webtech.gr14.model.address.Province;
import webtech.gr14.model.article.Location;
import webtech.gr14.service.admin.creation.LocationS;

@Controller
@RequestMapping("/admin/creation/locations")
public class LocationC {
	@Autowired
	private LocationS lS;

	@GetMapping
	public String list(Model model, @RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "15") int ps) {
		List<Location> locations = lS.getLocation(p, ps);
		model.addAttribute("locations", locations);
		List<Integer> pageList = lS.getPageList(p, ps);
		model.addAttribute("pages", pageList);
		return "/admin/creation/location/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		model.addAttribute("location", new Location());
		List<Province> provinces = lS.getProvinces();
		model.addAttribute("provinces", provinces);
		return "/admin/creation/location/add";
	}

	@PostMapping(value = "/add", consumes = { "multipart/form-data" })
	public String add(RedirectAttributes rdA, Location location, @RequestParam MultipartFile img) {
		rdA.addFlashAttribute("location", lS.createNew(location, img));
		return "redirect:/admin/creation/locations";
	}

	@GetMapping("/{lid}/edit")
	public String edit(Model model, @PathVariable int lid) {
		model.addAttribute("location", lS.lR.getOne(lid));
		List<Province> provinces = lS.getProvinces();
		model.addAttribute("provinces", provinces);
		return "/admin/creation/location/edit";
	}

	@PostMapping(value = "{lid}/edit", consumes = { "multipart/form-data" })
	public String edit(RedirectAttributes rdA, Location location, @RequestParam MultipartFile img) {
		int id = lS.saveChange(location, img);
		rdA.addFlashAttribute("id", id);
		return "redirect:/admin/creation/locations";
	}
}
