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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import webtech.gr14.model.article.Location;
import webtech.gr14.service.admin.creation.LocationS;

@Controller
@RequestMapping("/admin/manage/creation/locations")
public class LocationC {
	@Autowired
	private LocationS laS;

	@GetMapping
	public String list(Model model, @RequestParam(required = false, defaultValue = "1") int p,
			@RequestParam(required = false, defaultValue = "15") int ps) {
		List<Location> locations = laS.getLocation(p, ps);
		model.addAttribute("locations", locations);
		List<Integer> pageList = laS.getPageList(p, ps);
		model.addAttribute("pages", pageList);
		return "/admin/manage/creation/location/list";
	}

	@GetMapping("/add")
	public String add(Model model) {
		return "/admin/manage/creation/location/add";
	}

	@PostMapping("/add")
	public String add(RedirectAttributes rdA, Location location) {
		int id = laS.createNew(location);
		rdA.addFlashAttribute("id", id);
		return "redirect:/admin/manage/creation/locations";
	}

	@GetMapping("/{lid}/edit")
	public String edit(Model model, @PathVariable int lid) {
		return "/admin/manage/creation/location/edit";
	}

	@PostMapping("/{lid}/edit")
	public String edit(RedirectAttributes rdA, Location location) {
		int id = laS.saveChange(location);
		rdA.addFlashAttribute("id", id);
		return "redirect:/admin/manage/creation/locations";
	}
}
