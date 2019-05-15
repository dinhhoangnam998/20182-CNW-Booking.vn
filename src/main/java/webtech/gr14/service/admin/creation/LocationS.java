package webtech.gr14.service.admin.creation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import webtech.gr14.model.article.Location;
import webtech.gr14.repository.article.LocationR;

@Service
public class LocationS {

	@Autowired
	public LocationR lcR;

	public List<Location> getLocation(int p, int ps) {
		return lcR.findAll(PageRequest.of(p - 1, ps)).getContent();
	}

	public List<Integer> getPageList(int p, int ps) {
		// TODO Auto-generated method stub
		return null;
	}

	public int saveChange(Location location) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int createNew(Location location) {
		// TODO Auto-generated method stub
		return 0;
	}

}
