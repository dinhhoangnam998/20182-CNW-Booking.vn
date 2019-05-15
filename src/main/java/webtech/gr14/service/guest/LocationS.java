package webtech.gr14.service.guest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import webtech.gr14.model.article.Location;
import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.repository.article.LocationR;
import webtech.gr14.repository.hotel.HotelR;

@Service("guestLocationS")
public class LocationS {

	@Autowired
	public HotelR hR;

	@Autowired
	public LocationR lR;

	public List<Hotel> getHotelInProvince(int provinceId) {
		return hR.findByCommune_District_Province_Id(provinceId, PageRequest.of(0, 15));
	}

	public void toggle(int lid) {
		Location l = lR.getOne(lid);
		if (l.isActive()) {
			l.setActive(false);
		} else {
			l.setActive(true);
		}
		lR.save(l);
	}
}
