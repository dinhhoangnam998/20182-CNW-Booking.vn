package webtech.gr14.service.guest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import webtech.gr14.model.article.Location;
import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.repository.article.LocationR;
import webtech.gr14.repository.hotel.HotelR;

@Service
public class HomeS {

	private final int NUM_OF_SLIDE = 7;

	private final int NUM_OF_LOCATION = 9;
	@Autowired
	public HotelR hR;

	@Autowired
	public LocationR lR;

	public List<Hotel> getBestHotel() {
		return hR.findAll(PageRequest.of(0, NUM_OF_SLIDE, Sort.by(Direction.DESC, "score"))).getContent();
	}

	public List<Location> getLocation() {
		return lR.findByActive(true, PageRequest.of(0, NUM_OF_LOCATION));
	}

}
