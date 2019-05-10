package webtech.gr14.service.guest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import webtech.gr14.model.floor.Floor;
import webtech.gr14.model.floor.Room;
import webtech.gr14.model.hotel.Hotel;
import webtech.gr14.repository.address.CommuneR;
import webtech.gr14.repository.address.DistrictR;
import webtech.gr14.repository.address.ProvinceR;
import webtech.gr14.repository.floor.FloorR;
import webtech.gr14.repository.floor.RoomR;
import webtech.gr14.repository.hotel.HotelR;
import webtech.gr14.util.date.DateCommonUtil;
import webtech.gr14.util.enums.ActiveState;

@Service
public class SearchS {

	@Autowired
	public ProvinceR pR;

	@Autowired
	public DistrictR dR;

	@Autowired
	public CommuneR cR;

	@Autowired
	public HotelR hR;

	@Autowired
	public FloorR fR;

	@Autowired
	public RoomR rR;

	public List<Hotel> getSearchResults(HttpSession ss, String address, String dateRange, int numOfRoom,
			int numOfPeople) {
		ss.setAttribute("dateRange", dateRange);
		int cid = getCommuneIdFromAddress(address);
		Date begin = getBeginDateFromDateRange(dateRange);
		Date end = getEndDateFromDateRange(dateRange);
		List<Date> dateList = getDateListFromBeginToEnd(begin, end);
		return getSearchResult(cid, dateList, numOfRoom, numOfPeople);
	}

	public int getCommuneIdFromAddress(String address) {
		String[] part = address.split(",");
		String communeName = part[0];
		return cR.findByName(communeName);
	}

	public Date getBeginDateFromDateRange(String dateRange) {
		String[] part = dateRange.split("-");
		String dateInStr = part[0];
		return DateCommonUtil.stringToDate("MM/DD/YYYY", dateInStr);
	}

	public Date getEndDateFromDateRange(String dateRange) {
		String[] part = dateRange.split("-");
		String dateInStr = part[1];
		return DateCommonUtil.stringToDate("MM/DD/YYYY", dateInStr);
	}

	public List<Date> getDateListFromBeginToEnd(Date begin, Date end) {
		return DateCommonUtil.getDatesBetweenBeginAndEnd(begin, end);
	}

	public List<Hotel> getSearchResult(int cid, List<Date> dateList, int numOfRoom, int numOfPeople) {
		List<Hotel> result = new ArrayList<Hotel>();
		// each hotel
		List<Hotel> hotelsInCommune = hR.findByCommune_IdAndDeletedAndActiveStateNot(cid, false, ActiveState.BLOCKED);
		for (Hotel hotel : hotelsInCommune) {
			int roomRemain = 0, maxPeople = 0;
			// each floor
			List<Floor> floors = fR.findByHotel_Id(hotel.getId());
			for (Floor floor : floors) {
				int roomRemainOfThisFloor = 0;
				// each room
				List<Room> rooms = rR.findByFloor_IdAndDeleted(floor.getId(), false);
				for (Room room : rooms) {
					if (room.getRemainOpenDates().containsAll(dateList)) {
						roomRemainOfThisFloor += 1;
					}
				}

				roomRemain += roomRemainOfThisFloor;
				maxPeople += floor.getMaxPeople();
			}
			// if satisfy
			if (roomRemain > numOfRoom && maxPeople > numOfPeople) {
				result.add(hotel);
			}
		}
		return result;
	}

}
