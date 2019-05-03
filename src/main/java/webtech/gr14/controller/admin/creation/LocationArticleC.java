package webtech.gr14.controller.admin.creation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import webtech.gr14.service.admin.creation.LocationArticleS;

@Controller
public class LocationArticleC {
	@Autowired
	private LocationArticleS laS;

}
