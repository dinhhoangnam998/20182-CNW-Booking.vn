package webtech.gr14.service.guest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.repository.hotel.HotelR;

@Service("guestLocationS")
public class LocationS {

	@Autowired
	public HotelR hR;

	public List<Hotel> getHotelInProvince(int provinceId) {
		return hR.findByCommune_District_Province_Id(provinceId, PageRequest.of(0, 15));
	}
}
