package webtech.gr14.service.util;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.floor.Floor;
import webtech.gr14.model.floor.Room;
import webtech.gr14.repository.floor.FloorR;
import webtech.gr14.repository.floor.RoomR;
import webtech.gr14.repository.hotel.HotelR;
import webtech.gr14.util.date.DateCommonUtil;

@Service
public class HotelAPI {

	@Autowired
	private HotelR hR;

	@Autowired
	private FloorR fR;

	@Autowired
	private RoomR rR;

	public Integer[] getNumOfAvailableRoomAndMaxPeople(int hid, String dateRange) {
		Integer[] result = new Integer[2];
		int numOfAvailableRoom = 0, maxPeople = 0;
		List<Floor> floors = fR.findByHotel_IdAndDeleted(hid, false);
		for (Floor floor : floors) {
			int nOAROF = this.getNumOfAvailableRoomOfFloor(floor.getId(), dateRange);
			numOfAvailableRoom += nOAROF;
			maxPeople += nOAROF * floor.getMaxPeople();
		}
		result[0] = numOfAvailableRoom;
		result[1] = maxPeople;
		return result;
	}

	public int getNumOfAvailableRoomOfFloor(int fid, String dateRange) {
		int result = 0;
		List<Date> dateList = DateCommonUtil.getDatesFromStringDateRange(dateRange);
		List<Room> rooms = rR.findByFloor_IdAndDeleted(fid, false);
		for (Room room : rooms) {
			if (room.getRemainOpenDates().containsAll(dateList)) {
				result++;
			}
		}
		return result;
	}

}
