package webtech.gr14.service.admin.creation;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import webtech.gr14.model.address.Province;
import webtech.gr14.model.article.Location;
import webtech.gr14.repository.address.ProvinceR;
import webtech.gr14.repository.article.LocationR;
import webtech.gr14.service.util.StorageFileS;

@Service
public class LocationS {

	@Autowired
	public LocationR lR;

	@Autowired
	private StorageFileS sfS;

	@Autowired
	public ProvinceR pR;

	public List<Location> getLocation(int p, int ps) {
		return lR.findAll(PageRequest.of(p - 1, ps)).getContent();
	}

	public List<Integer> getPageList(int p, int ps) {
		return null;
	}

	public int saveChange(Location location, MultipartFile img) {
		Location origin = lR.getOne(location.getId());
		location.setCreateDate(origin.getCreateDate());
		if (img.getSize() != 0) {
			location.setImgURL("/images/location/" + sfS.saveFile(img, "location", location.getName()));
		} else {
			location.setImgURL(origin.getImgURL());
		}
		return lR.save(location).getId();
	}

	public Location createNew(Location location, MultipartFile img) {
		location.setCreateDate(new Date());
		if (img.getSize() != 0) {
			location.setImgURL("/images/location/" + sfS.saveFile(img, "location", location.getName()));
		}
		return lR.save(location);
	}

	public List<Province> getProvinces() {
		return pR.findAll();
	}

}
