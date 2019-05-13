package webtech.gr14.service.guest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.floor.Floor;
import webtech.gr14.model.floor.Room;
import webtech.gr14.repository.floor.FloorR;
import webtech.gr14.repository.floor.RoomR;
import webtech.gr14.repository.hotel.HotelR;
import webtech.gr14.util.date.DateCommonUtil;

@Service
public class HotelInfoS {

	@Autowired
	public HotelR hR;

	@Autowired
	public FloorR fR;

	@Autowired
	public RoomR rR;

	public List<Integer> getRemainRoomOfEachFloor(HttpSession ss, int hid) {
		String dateRange = (String) ss.getAttribute("dateRange");
		List<Date> reserveDates = DateCommonUtil.getDatesFromStringDateRange(dateRange);
		List<Floor> floors = fR.findByHotel_IdAndDeleted(hid, false);
		List<Integer> result = new ArrayList<Integer>();
		for (Floor floor : floors) {
			int numOfStatifyRoom = 0;
			List<Room> rooms = rR.findByFloor_IdAndDeleted(floor.getId(), false);
			for (Room room : rooms) {
				if (room.getRemainOpenDates().containsAll(reserveDates)) {
					numOfStatifyRoom += 1;
				}
			}
			result.add(numOfStatifyRoom);
		}
		return result;
	}

}
